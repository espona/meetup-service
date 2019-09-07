package es.backend.meetup.dto;

import java.util.List;

public class GroupsNearResultDTO extends ResultDTO<GroupResultDTO> {
	
	private double latitude;
	
    private double longitude;
    
    private int num;
    	
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

	@Override
	public String toString() {
		return "[" + super.toString() + " GroupNearResult [latitude=" + latitude + ", longitude=" + longitude + ", num=" + num + "]]";
	}
}
