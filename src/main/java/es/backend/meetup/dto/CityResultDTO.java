/**
* CityDTO is the object containing the city result
* containing the city data and the number of events
* to return in requests
* 
* @author Lucia de Espona
*
**/

package es.backend.meetup.dto;

public class CityResultDTO {
	
	private CityDTO city;
	
	private Long events;
	
	/**
	 * @return the city
	 */
	public CityDTO getCity() {
		return city;
	}
	
	/**
	 * @param city the city to set
	 */
	public void setCity(CityDTO city) {
		this.city = city;
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
	
	@Override
	public String toString() {
		return "CityResultDTO [city=" + city + ", events=" + events + "]";
	}
}
