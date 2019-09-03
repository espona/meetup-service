package es.backend.meetup.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import es.backend.meetup.stream.MeetupRSVPStreamListener;

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