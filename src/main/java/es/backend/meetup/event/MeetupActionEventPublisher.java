package es.backend.meetup.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

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
