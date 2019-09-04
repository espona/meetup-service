package es.backend.meetup.dto;

public class GroupDTO {
	
	private String group_id;
	
	private String group_name;
	
	private String group_city;
	
	private String group_state;
	
	private String group_country;
	
	private String group_urlname;
	
	private double group_lat;
	
	private double group_lon;
	
	/**
	 * @return the group_id
	 */
	public String getGroup_id() {
		return group_id;
	}
	
	/**
	 * @param group_id the group_id to set
	 */
	public void setGroup_id(String group_id) {
		this.group_id = group_id;
	}
	
	/**
	 * @return the group_name
	 */
	public String getGroup_name() {
		return group_name;
	}
	
	/**
	 * @param group_name the group_name to set
	 */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}
	
	/**
	 * @return the group_city
	 */
	public String getGroup_city() {
		return group_city;
	}
	
	/**
	 * @param group_city the group_city to set
	 */
	public void setGroup_city(String group_city) {
		this.group_city = group_city;
	}
	
	/**
	 * @return the group_state
	 */
	public String getGroup_state() {
		return group_state;
	}
	
	/**
	 * @param group_state the group_state to set
	 */
	public void setGroup_state(String group_state) {
		this.group_state = group_state;
	}
	
	/**
	 * @return the group_country
	 */
	public String getGroup_country() {
		return group_country;
	}
	
	/**
	 * @param group_country the group_country to set
	 */
	public void setGroup_country(String group_country) {
		this.group_country = group_country;
	}
	
	/**
	 * @return the group_urlname
	 */
	public String getGroup_urlname() {
		return group_urlname;
	}
	
	/**
	 * @param group_urlname the group_urlname to set
	 */
	public void setGroup_urlname(String group_urlname) {
		this.group_urlname = group_urlname;
	}
	
	/**
	 * @return the group_lat
	 */
	public double getGroup_lat() {
		return group_lat;
	}
	
	/**
	 * @param group_lat the group_lat to set
	 */
	public void setGroup_lat(double group_lat) {
		this.group_lat = group_lat;
	}
	
	/**
	 * @return the group_lon
	 */
	public double getGroup_lon() {
		return group_lon;
	}
	
	/**
	 * @param group_lon the group_lon to set
	 */
	public void setGroup_lon(double group_lon) {
		this.group_lon = group_lon;
	}
	
	@Override
	public String toString() {
		return "GroupObject [group_id=" + group_id + ", group_name=" + group_name + ", group_urlname=" + group_urlname 
				+ ", \n\t\t group_city=" + group_city + ", group_state=" + group_state + ", group_country=" + group_country
				+ ", group_lat=" + group_lat + ", group_lon=" + group_lon + "]";
	}
}
