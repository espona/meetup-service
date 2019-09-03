package es.backend.meetup.dto;

public class CityDTO {
	
	private String id;
	private String name;
	private String state;
	private String country;
	private Long events;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/**
	 * @return the events
	 */
	public Long getEvents() {
		return events;
	}
	/**
	 * @param events the events to set
	 */
	public void setEvents(Long events) {
		this.events = events;
	}
	public static String generateCityId(CityDTO city) {
		return (city.country + "_" + city.state + "_" + city.name);
	}
	
	@Override
	public String toString() {
		return "CityDTO [id=" + id + ", name=" + name + ", state=" + state + ", country=" + country + ", events="
				+ events + "]";
	}
	
}
