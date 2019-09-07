package es.backend.meetup.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.response.GroupResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import es.backend.meetup.dto.CitiesResultDTO;
import es.backend.meetup.dto.CityResultDTO;
import es.backend.meetup.dto.ErrorDTO;
import es.backend.meetup.dto.GroupResultDTO;
import es.backend.meetup.dto.GroupsNearResultDTO;
import es.backend.meetup.logic.SolrResultsConverter;
import es.backend.meetup.model.RsvpDocument;
import es.backend.meetup.repository.SolrMeetupRepository;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MeetupServiceImpl implements MeetupService {
		
	Logger logger = LoggerFactory.getLogger(MeetupServiceImpl.class);
	
	@Autowired
	SolrMeetupRepository solrMeetupRepository;
	
	@Autowired
	SolrResultsConverter solrResultsConverter;

	@Override
	public GroupsNearResultDTO getNearGroups(double latitude, double longitude, int num) {
    	    	
		List<GroupResultDTO> groupsResultList = new ArrayList<GroupResultDTO>();

		GroupResponse groupResponse = solrMeetupRepository.findGroupsNearby(latitude, longitude, num);
		logger.info("groupResponse = " + groupResponse);

		groupsResultList.addAll(solrResultsConverter.solrGroupResult2GroupDtoList(groupResponse));	
		
		// build the wrapper result dto
    	GroupsNearResultDTO groupNearResult = new GroupsNearResultDTO();
    	groupNearResult.setLatitude(latitude);
    	groupNearResult.setLongitude(longitude);
    	groupNearResult.setNum(num);
    	groupNearResult.setResults(groupsResultList);
    	
    	logger.info("Response: " + groupNearResult.toString());
    	
    	return groupNearResult;
	}

	@Override
	public CitiesResultDTO getTopCities(String date, int num) {
		
		String dateFormatString = "yyyy-MM-dd";
		
		List<CityResultDTO> cityResultList = new ArrayList<CityResultDTO>();
		
		try {
			
			// Parse date
			SimpleDateFormat format = new SimpleDateFormat(dateFormatString);
			Date dateObject = format.parse(date);    

			// query Solr and combine the results of faceting (order) and grouping (data)
			FacetPage<RsvpDocument> facetPageCities = solrMeetupRepository.findCityFacetsOnDate(dateObject, num);
			
			GroupPage<RsvpDocument> groupPageCities = solrMeetupRepository.findCitiesData(facetPageCities);
		
			cityResultList.addAll( solrResultsConverter.solrResults2CityDtoList(facetPageCities, groupPageCities));
			
		} catch (ParseException e) {
			
			e.printStackTrace();
			logger.warn("Date error: " + e.getMessage());
			CitiesResultDTO citiesResultError = new CitiesResultDTO();
			citiesResultError.setNum(num);
			citiesResultError.setDate(date);
			citiesResultError.setResults(null);
			citiesResultError.setError(new ErrorDTO(HttpStatus.BAD_REQUEST, "Wrong date format, should be " + dateFormatString));
			return citiesResultError;
		}
		    	
		// build the wrapper result dto
    	CitiesResultDTO citiesResult = new CitiesResultDTO();

    	citiesResult.setNum(num);
    	citiesResult.setDate(date);
    	citiesResult.setResults(cityResultList);
    	
    	logger.info("Response: " + citiesResult.toString());

    	return citiesResult;
	}
	
}
