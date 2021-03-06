package es.backend.meetup.logic.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.backend.meetup.stream.MeetupRSVPStreamListener;

/**
* MeetupActionEventListener listen for
* a {@link es.backend.meetup.logic.action.MeetupActionEvent} event to start/stop the
* Meetup stream listener
* 
* @author Lucia de Espona
*
**/
@Component
public class MeetupActionEventListener {
	
    private static final Logger logger = LoggerFactory.getLogger(MeetupActionEventListener.class);
    
	@Autowired
	MeetupRSVPStreamListener streamListener;

    @EventListener
    public void onApplicationEvent(MeetupActionEvent event) {
    	
        logger.info("Received Action Event - " + event.toString());
        
    	if(event.getAction().equals(MeetupActionEvent.Action.START)) {
    		streamListener.startListening();
    	} else {
    		streamListener.stopListening();
    	}
    }
}