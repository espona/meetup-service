/**
* MeetupServiceExceptionHandler defines the handling
* of errors during requests. An errorDTO is always returned
* 
* @author Lucia de Espona
*
**/

package es.backend.meetup.service;

import es.backend.meetup.dto.CitiesResultDTO;
import es.backend.meetup.dto.GroupsNearResultDTO;

public interface MeetupService {
	
	public GroupsNearResultDTO getNearGroups(double latitude, double longitude, int num);
	
	public CitiesResultDTO getTopCities(String date, int num);

}
