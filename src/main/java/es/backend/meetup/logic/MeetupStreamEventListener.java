package es.backend.meetup.logic;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.backend.meetup.dto.RsvpDTO;
import es.backend.meetup.repositories.RsvpDocument;
import es.backend.meetup.repositories.SolrMeetupRepository;
import es.backend.meetup.repositories.SolrMeetupRsvpRepository;
import es.backend.meetup.stream.event.MeetupStreamRsvpReceivedEvent;

@Component
public class MeetupStreamEventListener {
	
    private static final Logger logger = LoggerFactory.getLogger(MeetupStreamEventListener.class);
    
	@Value("${meetup.stream.url}")
	private String streamUrl;
	
	@Autowired
	private SolrMeetupRepository repository;

    @EventListener
    public void onApplicationEvent(MeetupStreamRsvpReceivedEvent event) {
    	
        logger.info("Received Action Event - " + event.toString());
        
        try {
        	
        	repository.save(this.streamRsvpObject2SolrRsvpDocument(event.getRsvpDTO()));
        	
        } catch (Exception e) {
        	
        	e.getStackTrace();
            logger.warn("Exception saving stream data as document: " + event.toString());
        }
        
    }
    
    private RsvpDocument streamRsvpObject2SolrRsvpDocument(RsvpDTO dto) {
    	
    	RsvpDocument document = new RsvpDocument();
    	
    	document.setId(Long.toString(dto.getRsvp_id()));
    	document.setRsvpMtime(dto.getMtime());
    	document.setResponse(dto.getResponse().equalsIgnoreCase("yes"));
    	document.setGuests(dto.getGuests());

    	document.setEventId(dto.getEvent().getEvent_id());
    	document.setEventName(dto.getEvent().getEvent_name());
    	document.setEventUrl(dto.getEvent().getEvent_url());
    	document.setEventTime(dto.getEvent().getTime());
    	document.setEventDate(millis2Date(dto.getEvent().getTime()));
    	
    	document.setGroupId(Long.toString(dto.getGroup().getGroup_id()));
    	document.setGroupName(dto.getGroup().getGroup_name());
    	document.setGroupUrlName(dto.getGroup().getGroup_urlname());
    	document.setGroupCountry(dto.getGroup().getGroup_country());
    	document.setGroupState(dto.getGroup().getGroup_state());
    	document.setGroupCity(dto.getGroup().getGroup_city());
    	document.setGroupCityId(document.generateCityId());;	
    	document.setGroupLat(dto.getGroup().getGroup_lat());
    	document.setGroupLon(dto.getGroup().getGroup_lon());
    	document.setGroupPosition(document.generateGroupPosition());
    	//TODO: document.setGroupTopicName(groupTopicName);
    	//TODO: document.setGroupTopicUrlkey(groupTopicUrlkey);
    	
    	document.setMemberId(Long.toString(dto.getMember().getMember_id()));
    	document.setMemberName(dto.getMember().getMember_name());
    	document.setMemberPhoto(dto.getMember().getPhoto());
    	//TODO: document.setMemberOtherServices(memberOtherServices);
    	
    	document.setVenueId(Long.toString(dto.getVenue().getVenue_id()));
    	document.setVenueName(dto.getVenue().getVenue_name());
    	document.setVenueLat(dto.getVenue().getLat());
    	document.setVenueLon(dto.getVenue().getLon());
    	
    	logger.info("Solr Document: " + document.toString() );

    	return document;
    	
    }
    
    private Date millis2Date(Long mtime) {
    	Date date = new Date(mtime);
    	return date;
    }
}