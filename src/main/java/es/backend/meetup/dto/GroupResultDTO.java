package es.backend.meetup.dto;

public class GroupResultDTO {
	
	private GroupDTO group;
	
	// Distance in km
	private Float distance;
	
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
	 * @return the distance
	 */
	public Float getDistance() {
		return distance;
	}
	
	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "GroupResultDTO [group=" + group + ", distance=" + distance + "]";
	}
}
