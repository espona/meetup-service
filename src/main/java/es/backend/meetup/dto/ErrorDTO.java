package es.backend.meetup.dto;

import org.springframework.http.HttpStatus;

public class ErrorDTO {
	
	private HttpStatus status;
	
	private String message;

	
	public ErrorDTO(HttpStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the status
	 */
	public HttpStatus getStatus() {
		return status;
	}



	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
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
		return "errorDTO [status=" + status + ", message=" + message + "]";
	}

}
