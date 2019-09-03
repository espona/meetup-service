package es.backend.meetup.stream.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import es.backend.meetup.dto.RsvpDTO;

@Component
public class MeetupStreamEventPublisher {

    private final ApplicationEventPublisher publisher;
	
    public MeetupStreamEventPublisher(ApplicationEventPublisher publisher) {
    	this.publisher = publisher;
    }
     
    public void publishRsvpReceivedEvent(RsvpDTO rsvpDTO) {
        publisher.publishEvent(new MeetupStreamRsvpReceivedEvent(this, rsvpDTO));
    }

}
