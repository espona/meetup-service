package es.backend.meetup.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.stereotype.Service;

import es.backend.meetup.dto.CitiesResultDTO;
import es.backend.meetup.dto.CityDTO;
import es.backend.meetup.dto.CityResultDTO;
import es.backend.meetup.dto.GroupDTO;
import es.backend.meetup.dto.GroupResultDTO;
import es.backend.meetup.dto.GroupsNearResultDTO;
import es.backend.meetup.model.RsvpDocument;
import es.backend.meetup.repositories.SolrMeetupRepository;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MeetupServiceImpl implements MeetupService {
		
	Logger logger = LoggerFactory.getLogger(MeetupServiceImpl.class);
	
	@Autowired
	SolrMeetupRepository solrMeetupRepository;

	@Override
	public GroupsNearResultDTO getNearGroups(double latitude, double longitude, int num) {
    	    	
		List<GroupResultDTO> groupsResultList = new ArrayList<GroupResultDTO>();

		try {
			
			GroupResponse groupResponse = solrMeetupRepository.findGroupsNearby(latitude, longitude, num);
			 logger.info("groupResponse = " + groupResponse);

			if (groupResponse != null) {
				for (GroupCommand groupCommand : groupResponse.getValues()) {
					for (Group group : groupCommand.getValues()) { 
						if (!group.getResult().isEmpty()) {
							// We only need the first document of each group to get the meetup group data
							SolrDocument document = group.getResult().get(0);
																							 
							GroupDTO dto = new GroupDTO();
							dto.setGroup_id((String) document.getFieldValue("group_id"));
							dto.setGroup_name((String) document.getFieldValue("group_name"));
							dto.setGroup_country((String) document.getFieldValue("group_country"));
							dto.setGroup_state((String) document.getFieldValue("group_state"));
							dto.setGroup_city((String) document.getFieldValue("group_city"));
							dto.setGroup_lat((Double) document.getFieldValue("group_lat"));
							dto.setGroup_lon((Double) document.getFieldValue("group_lon"));
							dto.setGroup_urlname((String) document.getFieldValue("group_urlname"));
							
							GroupResultDTO resultDto = new GroupResultDTO();
							resultDto.setDistance((Float) document.getFieldValue("distance"));
							resultDto.setGroup(dto);
		
							groupsResultList.add(resultDto);
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Solr error: " + e.getMessage());
		}

		
    	GroupsNearResultDTO groupNearResult = new GroupsNearResultDTO();
    	groupNearResult.setLatitude(latitude);
    	groupNearResult.setLongitude(longitude);
    	groupNearResult.setNum(num);
    	groupNearResult.setGroups(groupsResultList);
    	
    	logger.info("Response: " + groupNearResult.toString());
    	
    	return groupNearResult;

	}

	@Override
	public CitiesResultDTO getTopCities(String date, int num) {
		
		List<CityResultDTO> cityResultList = new ArrayList<CityResultDTO>();
		
		try {
			
			// Parse date
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateObject = format.parse(date);    

			FacetPage<RsvpDocument> facetPageCities = solrMeetupRepository.findCityFacetsOnDate(dateObject, num);
			
			GroupPage<RsvpDocument> groupPageCities = solrMeetupRepository.findCitiesData(facetPageCities);
		
			// get the map of city ids -> RsvpDocument containing the city data
			HashMap<String, RsvpDocument> cityData = new HashMap<String, RsvpDocument>(); 
			
			List<GroupEntry<RsvpDocument>>  cityGroupEntries = groupPageCities.getGroupResult("group_city_id").getGroupEntries().getContent();			
			Iterator<GroupEntry<RsvpDocument>> groupListIterator = cityGroupEntries.iterator();
			while (groupListIterator.hasNext()) {
				GroupEntry<RsvpDocument> groupEntry = groupListIterator.next();
				if (!groupEntry.getResult().getContent().isEmpty()) {
					// We only need the first document of each group to get the meetup group data
					RsvpDocument document = groupEntry.getResult().getContent().get(0);
					cityData.put(document.getGroupCityId(), document);
				}
			}
			
			// add the facet list (ordered) results and include the data from the group query
			Collection<Page<? extends FacetEntry>> facets = facetPageCities.getAllFacets();		
			Iterator<Page<? extends FacetEntry>> iterator = facets.iterator();
			while (iterator.hasNext()) {
				List<FacetEntry> facetList = cast(iterator);
				Iterator<FacetEntry> facetListIterator = facetList.iterator();
				while (facetListIterator.hasNext()) {
					FacetEntry facetEntry = facetListIterator.next();
					String cityId = facetEntry.getValue();
					Long count = facetEntry.getValueCount();
					if (cityData.containsKey(cityId)) {
						RsvpDocument data = cityData.get(cityId);
						
						CityDTO dto = new CityDTO();
						dto.setId(data.getGroupCityId());
						dto.setCountry(data.getGroupCountry());
						dto.setState(data.getGroupState());
						dto.setName(data.getGroupCity());
						
						CityResultDTO resultDto = new CityResultDTO();
						resultDto.setEvents(count);
						resultDto.setCity(dto);

						cityResultList.add(resultDto);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Solr error: " + e.getMessage());
		}

		    	
    	CitiesResultDTO citiesResult = new CitiesResultDTO();

    	citiesResult.setNum(num);
    	citiesResult.setDate(date);
    	citiesResult.setCities(cityResultList);
    	
    	logger.info("Response: " + citiesResult.toString());

    	return citiesResult;
	}

	@SuppressWarnings("unchecked")
	private List<FacetEntry> cast(Iterator<Page<? extends FacetEntry>> iterator) {
		try {
			return ((List<FacetEntry>) iterator.next().getContent());
		} catch (Exception e) {
			// if casts fail return empty list
			e.printStackTrace();
			logger.warn("Cannot cast object to List<FacetEntry>");
			return (new ArrayList<FacetEntry>());
		}
	}
	
}
