package es.backend.meetup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import es.backend.meetup.dto.ListenerStatusDTO;
import es.backend.meetup.event.MeetupActionEvent;

@RestController
public class Controller {
    
	Logger logger = LoggerFactory.getLogger(Controller.class);
		
	@Autowired
    private ApplicationEventPublisher publisher;
	
	
	@Autowired
	MeetupService meetupService;

    @RequestMapping("/near")
    public ResponseEntity<Object> getNearGroups(@RequestParam(value="lat", required=true) double latitude,
    											@RequestParam(value="lon", required=true) double longitude,
    											@RequestParam(value="num", defaultValue = "5") int num) {
    	try {
    		return ResponseEntity.ok(meetupService.getNearGroups(latitude, longitude, num));
    	} catch (Exception e) {
    		logger.warn("Exception getting near groups: " + e.getMessage());
    		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
    	}
    }

    @RequestMapping("/topCities")
    public ResponseEntity<Object> getTopCities(@RequestParam(value="date", required=true) String date,
    									 	   @RequestParam(value="num", defaultValue = "5") int num) {
    	try {
    		return ResponseEntity.ok(meetupService.getTopCities(date, num));
    	} catch (Exception e) {
    		logger.warn("Exception getting top cties: " + e.getMessage());
    		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
    	}
    }

    @RequestMapping("/listen")
    public ListenerStatusDTO startListener() {
    	
    	logger.info("Got a start listening request, sending event");
    	
        publisher.publishEvent(new MeetupActionEvent(this, MeetupActionEvent.Action.START));
        
    	ListenerStatusDTO listenerStatusDTO = new ListenerStatusDTO();
    	listenerStatusDTO.setSuccess(true);
    	listenerStatusDTO.setMessage("Done start listener");
    	
    	logger.info("Response: " + listenerStatusDTO.toString());

    	return listenerStatusDTO;
    }

    @RequestMapping("/stop")
    public ListenerStatusDTO stopListener() {
    	
    	logger.info("Got a stop listening request");
    	
        publisher.publishEvent(new MeetupActionEvent(this, MeetupActionEvent.Action.STOP));
    	
    	ListenerStatusDTO listenerStatusDTO = new ListenerStatusDTO();
    	listenerStatusDTO.setSuccess(true);
    	listenerStatusDTO.setMessage("Done stop listener");
    	
    	logger.info("Response: " + listenerStatusDTO.toString());

    	return listenerStatusDTO;
    }    

}
