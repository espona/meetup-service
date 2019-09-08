package es.backend.meetup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.backend.meetup.dto.ErrorDTO;
import es.backend.meetup.dto.ListenerStatusDTO;
import es.backend.meetup.logic.action.MeetupActionEvent;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
* StreamListenerController defines the API to
* request an action (start/stop) on the RSVP listener.
* Methods are implemented here directly due to its 
* simplicity.
* 
* @author Lucia de Espona
*
**/
@RequestMapping("/listener")
@RestController
public class StreamListenerController {
    
	Logger logger = LoggerFactory.getLogger(MeetupServiceController.class);
		
	@Autowired
    private ApplicationEventPublisher publisher;

	@GetMapping("/start")
    @ApiOperation("Start the stream listener")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Successful retrieval of demand", response=ListenerStatusDTO.class),
            @ApiResponse(code = 404, message = "Demand does not exist", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDTO.class) 
        }
    )
    public ResponseEntity<Object> startListener() {
    	    	
    	try {
    		
	        publisher.publishEvent(new MeetupActionEvent(this, MeetupActionEvent.Action.START));
	        
	    	ListenerStatusDTO listenerStatusDTO = new ListenerStatusDTO();
	    	listenerStatusDTO.setSuccess(true);
	    	listenerStatusDTO.setMessage("Done start listener");
	    	
    	    return ResponseEntity.ok(listenerStatusDTO);
    	   
    	} catch (Exception e) {
    		
    		logger.warn("Exception starting the stream listener: " + e.getMessage());
    		return ResponseEntity.status(500).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error: " + e.getLocalizedMessage()));
    	}
    }

    @GetMapping("/stop")
    @ApiOperation("Stop the stream listener")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Successful retrieval of demand", response=ListenerStatusDTO.class),
            @ApiResponse(code = 404, message = "Demand does not exist", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDTO.class) 
        }
    )
    public ResponseEntity<Object> stopListener() {
    	    	
    	try {
    		
	        publisher.publishEvent(new MeetupActionEvent(this, MeetupActionEvent.Action.STOP));
	    	
	    	ListenerStatusDTO listenerStatusDTO = new ListenerStatusDTO();
	    	listenerStatusDTO.setSuccess(true);
	    	listenerStatusDTO.setMessage("Done stop listener");
	    	
	    	return ResponseEntity.ok(listenerStatusDTO);
	    	
	 	} catch (Exception e) {
	 		
	 		logger.warn("Exception stopping the stream listener: " + e.getMessage());
    		return ResponseEntity.status(500).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error: " + e.getLocalizedMessage()));
	 	}
    	
    }    

}
