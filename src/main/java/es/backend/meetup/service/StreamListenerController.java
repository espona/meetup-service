package es.backend.meetup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import es.backend.meetup.dto.ListenerStatusDTO;
import es.backend.meetup.event.MeetupActionEvent;

@RequestMapping("/listener")
@RestController
public class StreamListenerController {
    
	Logger logger = LoggerFactory.getLogger(MeetupServiceController.class);
		
	@Autowired
    private ApplicationEventPublisher publisher;

	@GetMapping("/start")
    public ResponseEntity<Object> startListener() {
    	
    	logger.info("Got a start listening request, sending event");
    	
    	try {
    		
	        publisher.publishEvent(new MeetupActionEvent(this, MeetupActionEvent.Action.START));
	        
	    	ListenerStatusDTO listenerStatusDTO = new ListenerStatusDTO();
	    	listenerStatusDTO.setSuccess(true);
	    	listenerStatusDTO.setMessage("Done start listener");
	    	
    	    return ResponseEntity.ok(listenerStatusDTO);
    	   
    	} catch (Exception e) {
    		
    		logger.warn("Exception starting the stream listener: " + e.getMessage());
    		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
    	}
    }

    @GetMapping("/stop")
    public ResponseEntity<Object> stopListener() {
    	
    	logger.info("Got a stop listening request");
    	
    	try {
    		
	        publisher.publishEvent(new MeetupActionEvent(this, MeetupActionEvent.Action.STOP));
	    	
	    	ListenerStatusDTO listenerStatusDTO = new ListenerStatusDTO();
	    	listenerStatusDTO.setSuccess(true);
	    	listenerStatusDTO.setMessage("Done stop listener");
	    	
	    	return ResponseEntity.ok(listenerStatusDTO);
	    	
	 	} catch (Exception e) {
	 		
	 		logger.warn("Exception stopping the stream listener: " + e.getMessage());
	 		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
	 	}
    	
    }    

}
