package es.backend.meetup.dto;

import java.util.List;

public class GroupsNearResultDTO {
	
	private double latitude;
    private double longitude;
    private int num;
    private List<GroupResultDTO> groups;
	
    /**
	 * @return the latitude
	 */
	public double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the groups
	 */
	public List<GroupResultDTO> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(List<GroupResultDTO> groups) {
		this.groups = groups;
	}

	@Override
	public String toString() {
		return "GroupNearResult [latitude=" + latitude + ", longitude=" + longitude + ", num=" + num + ", groups="
				+ groups + "]";
	}

}
