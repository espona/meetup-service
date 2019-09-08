/**
* ListenerStatusDTO is the object returned when requesting for
* an action (start/stop) on the stream listener.
* 
* @author Lucia de Espona
* 
*/


package es.backend.meetup.dto;

import io.swagger.annotations.ApiModel;

@ApiModel(description = "Status of the Meetup  RSVP stream listener")
public class ListenerStatusDTO {

	private boolean success;
	
	private String message;
	
	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}
	
	/**
	 * @param success the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "listenerStatusDTO [success=" + success + ", message=" + message + "]";
	}
}
