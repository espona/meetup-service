package es.backend.meetup.dto;

public class RsvpDTO {
		
	private Long rsvp_id;
	
	private int api_version;
	
	private EventDTO event;
	
	private GroupDTO group;
	
	private int guests;
	
	private MemberDTO member;
	
	private long mtime;
	
	private String Response;
	
	private VenueDTO venue;
	
	/**
	 * @return the rsvp_id
	 */
	public Long getRsvp_id() {
		return rsvp_id;
	}
	
	/**
	 * @param rsvp_id the rsvp_id to set
	 */
	public void setRsvp_id(Long rsvp_id) {
		this.rsvp_id = rsvp_id;
	}
	
	/**
	 * @return the api_version
	 */
	public int getApi_version() {
		return api_version;
	}
	
	/**
	 * @param api_version the api_version to set
	 */
	public void setApi_version(int api_version) {
		this.api_version = api_version;
	}
	
	/**
	 * @return the event
	 */
	public EventDTO getEvent() {
		return event;
	}
	
	/**
	 * @param event the event to set
	 */
	public void setEvent(EventDTO event) {
		this.event = event;
	}
	
	/**
	 * @return the group
	 */
	public GroupDTO getGroup() {
		return group;
	}
	
	/**
	 * @param group the group to set
	 */
	public void setGroup(GroupDTO group) {
		this.group = group;
	}
	
	/**
	 * @return the guests
	 */
	public int getGuests() {
		return guests;
	}
	
	/**
	 * @param guests the guests to set
	 */
	public void setGuests(int guests) {
		this.guests = guests;
	}
	
	/**
	 * @return the member
	 */
	public MemberDTO getMember() {
		return member;
	}
	
	/**
	 * @param member the member to set
	 */
	public void setMember(MemberDTO member) {
		this.member = member;
	}
	
	/**
	 * @return the mtime
	 */
	public long getMtime() {
		return mtime;
	}
	
	/**
	 * @param mtime the mtime to set
	 */
	public void setMtime(long mtime) {
		this.mtime = mtime;
	}
	
	/**
	 * @return the response
	 */
	public String getResponse() {
		return Response;
	}
	
	/**
	 * @param response the response to set
	 */
	public void setResponse(String response) {
		Response = response;
	}

	/**
	 * @return the venue
	 */
	public VenueDTO getVenue() {
		return venue;
	}
	
	/**
	 * @param venue the venue to set
	 */
	public void setVenue(VenueDTO venue) {
		this.venue = venue;
	}
	
	@Override
	public String toString() {
		return "RsvpDTO [api_version=" + api_version + ", event=" + event + ", group=" + group + ", guests=" + guests
				+ ", member=" + member + ", mtime=" + mtime + ", Response=" + Response + ", rsvp_id=" + rsvp_id
				+ ", venue=" + venue + "]";
	}
}
