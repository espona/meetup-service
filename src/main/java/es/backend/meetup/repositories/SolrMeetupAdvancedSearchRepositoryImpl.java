package es.backend.meetup.repositories;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.beans.Field;
import org.apache.solr.client.solrj.request.QueryRequest;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.MapSolrParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.geo.GeoConverters;
import org.springframework.data.solr.core.geo.Point;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.DistanceField;
import org.springframework.data.solr.core.query.FacetOptions;
import org.springframework.data.solr.core.query.FacetQuery;
import org.springframework.data.solr.core.query.FilterQuery;
import org.springframework.data.solr.core.query.GroupOptions;
import org.springframework.data.solr.core.query.SimpleFacetQuery;
import org.springframework.data.solr.core.query.SimpleField;
import org.springframework.data.solr.core.query.SimpleFilterQuery;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SimpleStringCriteria;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupPage;

import es.backend.meetup.model.RsvpDocument;
import es.backend.meetup.service.MeetupServiceImpl;

public class SolrMeetupAdvancedSearchRepositoryImpl implements SolrMeetupAdvancedSearchRepository {

	@Autowired
	private SolrOperations solrTemplate;
	
	@Autowired
	private SolrClient solrClient;
	
	@Value("${solr.collection.rsvp}")
	private String collection;
	
	Logger logger = LoggerFactory.getLogger(SolrMeetupAdvancedSearchRepositoryImpl.class);
	
	@Override
	public FacetPage<RsvpDocument> findCityFacetsOnDate(Date date, int num) {
		
		
		FacetQuery query = new SimpleFacetQuery(new Criteria(Criteria.WILDCARD).expression(Criteria.WILDCARD));
		query.setFacetOptions(new FacetOptions().addFacetOnField("group_city_id").setFacetLimit(num));
		
		// get date period
		SimpleDateFormat solrDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = solrDateFormat.format(date);
		String initDateTime = dateString + "T00:00:00Z";
		String endDateTime = dateString + "T23:59:59Z";

		logger.info("Solr Query: [" + initDateTime + " - " + endDateTime + "]" );
				
		FilterQuery fq = new SimpleFilterQuery(new Criteria("event_date").expression("[" + initDateTime +" TO " + endDateTime + "]")
												.connect().and("response").is(true));
		query.addFilterQuery(fq);
		
		// offset always zero, rows to minimum since we only want the facets
		query.setRows(1).setOffset(new Long(0));
		
		FacetPage<RsvpDocument> page = solrTemplate.queryForFacetPage(collection, query, RsvpDocument.class);
		return page;
	}

	@Override
	public GroupPage<RsvpDocument> findCitiesData(FacetPage<RsvpDocument> cities) {
		
		
		// get the list of city ids
		List<String> cityIdList = new ArrayList<String>();
		Collection<Page<? extends FacetEntry>> facets = cities.getAllFacets();		
		Iterator<Page<? extends FacetEntry>> iterator = facets.iterator();
		while (iterator.hasNext()) {
			List<FacetEntry> facetList = (List<FacetEntry>) iterator.next().getContent();
			Iterator<FacetEntry> listIterator = facetList.iterator();
			while (listIterator.hasNext()) {
				FacetEntry facetEntry = listIterator.next();
				String cityId = facetEntry.getValue();
				if (!cityIdList.contains(cityId)) {
					cityIdList.add(cityId);
				}
			}
		}
		logger.info("Got cities = " + cityIdList);
		
		//http://localhost:8988/solr/item_meetup_rsvp/select?q=*:*&rows=10&group=true&group.field=group_city&group.limit=1
		
		SimpleQuery groupQuery = new SimpleQuery(new Criteria("group_city_id").in(cityIdList));
		//SimpleQuery groupQuery = new SimpleQuery(new SimpleStringCriteria("*:*"));
		GroupOptions groupOptions = new GroupOptions().addGroupByField("group_city_id").setLimit(1);
		groupQuery.setGroupOptions(groupOptions);
		groupQuery.setRows(cityIdList.size()).setOffset(new Long(0));

		GroupPage<RsvpDocument> page = solrTemplate.queryForGroupPage(collection, groupQuery, RsvpDocument.class);

		return page;
	}

	@Override
	public GroupResponse findGroupsNearby(double latitude, double longitude, int num) {
		// http://localhost:8988/solr/item_meetup_rsvp/select?q=*:*&rows=3&group=true&group.field=group_id&pt=-8.40%2043.37&sfield=position&sort=geodist()%20asc&fl=group_name,group_city_id,distance:geodist()
				
		
		// Build query
		final Map<String, String> queryParamMap = new HashMap<String, String>();

		// Free text search
		queryParamMap.put("q", "*:*");
		
		// Group
		queryParamMap.put("group", "true");
		queryParamMap.put("group.field", "group_id");
		queryParamMap.put("group.limit", "1");

		// Results maximum number and offset
		queryParamMap.put("start", "0");
		queryParamMap.put("rows", "" + num);

		// sort by score when there is a search term
		queryParamMap.put("sort", "geodist() asc");
		queryParamMap.put("pt", longitude + " " + latitude);
		queryParamMap.put("sfield", "position");
		queryParamMap.put("fl", "*,distance:geodist()");

		logger.info(" -> SOLR QUERY: " + queryParamMap.toString());

		// Build and perform request
		MapSolrParams queryParams = new MapSolrParams(queryParamMap);
		QueryRequest request = new QueryRequest(queryParams);
		
		try {
			final QueryResponse response = (QueryResponse) request.process(solrClient, collection);
			GroupResponse groupResponse = response.getGroupResponse();
			logger.info("GOT " + response.getGroupResponse());
			
			return groupResponse;

		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
			logger.warn("Exception when processing Solr request: " + e.getMessage());
			return null;
		}
		
	}

}
