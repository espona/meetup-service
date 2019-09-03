package es.backend.meetup.dto;

public class VenueDTO {
	
	private long venue_id;
	private String venue_name;
	private double lat;
	private double lon;
	
	/**
	 * @return the venue_id
	 */
	public long getVenue_id() {
		return venue_id;
	}
	/**
	 * @param venue_id the venue_id to set
	 */
	public void setVenue_id(long venue_id) {
		this.venue_id = venue_id;
	}
	/**
	 * @return the venue_name
	 */
	public String getVenue_name() {
		return venue_name;
	}
	/**
	 * @param venue_name the venue_name to set
	 */
	public void setVenue_name(String venue_name) {
		this.venue_name = venue_name;
	}
	/**
	 * @return the lat
	 */
	public double getLat() {
		return lat;
	}
	/**
	 * @param lat the lat to set
	 */
	public void setLat(double lat) {
		this.lat = lat;
	}
	/**
	 * @return the lon
	 */
	public double getLon() {
		return lon;
	}
	/**
	 * @param lon the lon to set
	 */
	public void setLon(double lon) {
		this.lon = lon;
	}
	
	@Override
	public String toString() {
		return "VenueDTO [venue_id=" + venue_id + ", venue_name=" + venue_name + ", lat=" + lat + ", lon=" + lon + "]";
	}
	
}
