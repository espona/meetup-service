/**
* MeetupStreamEventPublisher publishes a MeetupStreamRsvpReceivedEvent
* every time a new RSVP Meetup object is received from the stream
* 
* @author Lucia de Espona
*
**/

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
