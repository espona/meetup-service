package es.backend.meetup.service;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.backend.meetup.dto.CitiesResultDTO;
import es.backend.meetup.dto.ErrorDTO;
import es.backend.meetup.dto.GroupsNearResultDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
* MeetupServiceController defines the API to
* request Meetup RSVP data
* 
* @author Lucia de Espona
*
**/
@RequestMapping("/meetup")
@RestController
public class MeetupServiceController {
    
	Logger logger = LoggerFactory.getLogger(MeetupServiceController.class);
		
	@Autowired
	MeetupService meetupService;

    @GetMapping("/near")
    @ApiOperation("Returns Given the num closest Groups in distance to a given location (latitude, longitude)")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Successful retrieval of demand", response=CitiesResultDTO.class),
            @ApiResponse(code = 404, message = "Demand does not exist", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDTO.class) 
        }
    )
    public ResponseEntity<Object> getNearGroups( @RequestParam(value="lat", required=true) Double latitude,
    											 @RequestParam(value="lon", required=true) Double longitude,
    											 @RequestParam(value="num", required=true) Integer num) {
    	try {
    		
    		return ResponseEntity.ok(meetupService.getNearGroups(latitude, longitude, num));
    		
    	} catch (Exception e) {
    		
    		logger.warn("Exception getting near groups: " + e.getMessage());
    		return ResponseEntity.status(500).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
    				                               "Internal Server Error: " + e.getLocalizedMessage()));
    	}
    }

    
    @GetMapping("/topCities")
    @ApiOperation("Returns the top num cities sorted by the number of people attending events in that city on the given date (ISO format yyyy-mm-dd)")
    @ApiResponses(value = { 
            @ApiResponse(code = 200, message = "Successful retrieval of demand", response=GroupsNearResultDTO.class),
            @ApiResponse(code = 404, message = "Demand does not exist", response = ErrorDTO.class),
            @ApiResponse(code = 500, message = "Internal server error", response = ErrorDTO.class) 
        }
    )
    public ResponseEntity<Object> getTopCities(@ApiParam("Date in ISO format YYYY-MM-DD") @RequestParam(value="date", required=true) String date,
    										   @RequestParam(value="num", required=true) Integer num) {
    	try {
    		
    		return ResponseEntity.ok(meetupService.getTopCities(date, num));
    		
    	} catch (Exception e) {
    		
    		logger.warn("Exception getting top cities: " + e.getMessage());
    		return ResponseEntity.status(500).body(new ErrorDTO(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Internal Server Error: " + e.getLocalizedMessage()));
    	}
    }

}
