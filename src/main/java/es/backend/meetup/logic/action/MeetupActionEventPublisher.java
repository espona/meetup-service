package es.backend.meetup.logic.action;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
* MeetupActionEventPublisher launches action event
* to start/stop the Meetup stream listener when a
* event is received.
* 
* @author Lucia de Espona
*
**/
@Component
public class MeetupActionEventPublisher {

    private final ApplicationEventPublisher publisher;
	
    public MeetupActionEventPublisher(ApplicationEventPublisher publisher) {
    	this.publisher = publisher;
    }

    public void publishActionStartEvent() {
        publisher.publishEvent(new MeetupActionEvent(this, MeetupActionEvent.Action.START));
    }
    
    public void publishActionEvent(MeetupActionEvent.Action action) {
        publisher.publishEvent(new MeetupActionEvent(this, action));
    }

}
