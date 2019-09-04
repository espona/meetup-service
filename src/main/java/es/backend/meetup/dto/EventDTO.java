package es.backend.meetup.dto;

public class EventDTO {
	
	private String event_id;
	
	private String event_name;
	
	private String event_url;
	
	private long time;
	
	/**
	 * @return the event_id
	 */
	public String getEvent_id() {
		return event_id;
	}
	
	/**
	 * @param event_id the event_id to set
	 */
	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}
	
	/**
	 * @return the event_name
	 */
	public String getEvent_name() {
		return event_name;
	}
	
	/**
	 * @param event_name the event_name to set
	 */
	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}
	
	/**
	 * @return the event_url
	 */
	public String getEvent_url() {
		return event_url;
	}
	
	/**
	 * @param event_url the event_url to set
	 */
	public void setEvent_url(String event_url) {
		this.event_url = event_url;
	}
	
	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}
	
	/**
	 * @param time the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}
	
	@Override
	public String toString() {
		return "EventObject [event_id=" + event_id + ", time=" + time + ", event_name=" + event_name
				+ ", event_url=" + event_url + "]";
	}
}
