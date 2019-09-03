package es.backend.meetup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import es.backend.meetup.dto.CitiesResultDTO;
import es.backend.meetup.dto.GroupsNearResultDTO;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class MeetupServiceImpl implements MeetupService {
		
	Logger logger = LoggerFactory.getLogger(MeetupServiceImpl.class);

	@Override
	public GroupsNearResultDTO getNearGroups(double latitude, double longitude, int num) {
    	    	
		
/*		try {
			Optional<RsvpDocument> document = solrMeetupRsvpRepository.findById("RSVP_1");
			RsvpDocument rsvpDocument = document.get();
			logger.info("got from Solr: " + rsvpDocument.toString());
			
			rsvpDocument.setMemberName("Lucia Espona");
			solrMeetupRsvpRepository.save(rsvpDocument);
			
			document = solrMeetupRsvpRepository.findById("RSVP_1");
			rsvpDocument = document.get();
			logger.info("got after update from Solr: " + rsvpDocument.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.warn("Solr error: " + e.getMessage());
		}
*/		
		

		
    	GroupsNearResultDTO groupNearResult = new GroupsNearResultDTO();
    	groupNearResult.setLatitude(latitude);
    	groupNearResult.setLongitude(longitude);
    	groupNearResult.setNum(num);
    	
    	logger.info("Response: " + groupNearResult.toString());
    	
    	return groupNearResult;

	}

	@Override
	public CitiesResultDTO getTopCities(String date, int num) {
		    	
    	CitiesResultDTO citiesResult = new CitiesResultDTO();

    	citiesResult.setNum(num);
    	citiesResult.setDate(date);
    	
    	logger.info("Response: " + citiesResult.toString());

    	return citiesResult;
	}

}
