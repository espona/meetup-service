package es.backend.meetup.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.stereotype.Service;

import es.backend.meetup.dto.CitiesResultDTO;
import es.backend.meetup.dto.CityDTO;
import es.backend.meetup.dto.GroupsNearResultDTO;
import es.backend.meetup.repositories.RsvpDocument;
import es.backend.meetup.repositories.SolrMeetupRepository;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MeetupServiceImpl implements MeetupService {
		
	Logger logger = LoggerFactory.getLogger(MeetupServiceImpl.class);
	
	@Autowired
	SolrMeetupRepository solrMeetupRepository;

	@Override
	public GroupsNearResultDTO getNearGroups(double latitude, double longitude, int num) {
    	    	
		

		
    	GroupsNearResultDTO groupNearResult = new GroupsNearResultDTO();
    	groupNearResult.setLatitude(latitude);
    	groupNearResult.setLongitude(longitude);
    	groupNearResult.setNum(num);
    	
    	logger.info("Response: " + groupNearResult.toString());
    	
    	return groupNearResult;

	}

	@Override
	public CitiesResultDTO getTopCities(String date, int num) {
		
		List<CityDTO> cityResultList = new ArrayList<CityDTO>();
		
		try {
			
			// Parse date
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date dateObject = format.parse(date);    

			FacetPage<RsvpDocument> facetPageCities = solrMeetupRepository.findCityFacetsOnDate(dateObject, num);
			
			GroupPage<RsvpDocument> groupPageCities = solrMeetupRepository.findCitiesData(facetPageCities);
		
			// get the map of city ids
			HashMap<String, RsvpDocument> cityData = new HashMap<String, RsvpDocument>(); 
			
			List<GroupEntry<RsvpDocument>>  cityGroupEntries = groupPageCities.getGroupResult("group_city_id").getGroupEntries().getContent();
			
			Iterator<GroupEntry<RsvpDocument>> groupListIterator = cityGroupEntries.iterator();
			while (groupListIterator.hasNext()) {
				GroupEntry<RsvpDocument> groupEntry = groupListIterator.next();
				if (!groupEntry.getResult().getContent().isEmpty()) {
					RsvpDocument document = groupEntry.getResult().getContent().get(0);
					cityData.put(document.getGroupCityId(), document);
				}
			}
			
			Collection<Page<? extends FacetEntry>> facets = facetPageCities.getAllFacets();		
			Iterator<Page<? extends FacetEntry>> iterator = facets.iterator();
			while (iterator.hasNext()) {
				List<FacetEntry> facetList = (List<FacetEntry>) iterator.next().getContent();
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
						dto.setEvents(count);
						
						cityResultList.add(dto);
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
	
}
