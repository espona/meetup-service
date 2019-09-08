/**
* MeetupStreamRsvpReceivedEvent entity contains 
* a RSVP Meetup object received from the stream
* 
* @author Lucia de Espona
*
**/

package es.backend.meetup.stream.event;

import org.springframework.context.ApplicationEvent;

import es.backend.meetup.dto.RsvpDTO;

/**
 * @author lucia
 * event published when a RSVP is received on the stream
 */

public class MeetupStreamRsvpReceivedEvent extends ApplicationEvent {

	private static final long serialVersionUID = 5603396312298108522L;

	private RsvpDTO rsvpDTO;
	
	public MeetupStreamRsvpReceivedEvent(Object source, RsvpDTO rsvpDTO) {
		super(source);
		this.rsvpDTO = rsvpDTO;
	}

	/**
	 * @return the rsvpDTO
	 */
	public RsvpDTO getRsvpDTO() {
		return rsvpDTO;
	}

	/**
	 * @param rsvpDTO the rsvpDTO to set
	 */
	public void setRsvpDTO(RsvpDTO rsvpDTO) {
		this.rsvpDTO = rsvpDTO;
	}

	@Override
	public String toString() {
		return "MeetupStreamRsvpReceivedEvent [rsvpDTO=" + rsvpDTO + "]";
	}

}
