package es.backend.meetup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.http.ResponseEntity;

@RequestMapping("/meetup")
@RestController
public class MeetupServiceController {
    
	Logger logger = LoggerFactory.getLogger(MeetupServiceController.class);
		
	@Autowired
	MeetupService meetupService;

    @GetMapping("/near")
    @ApiOperation("Returns Given the num closest Groups in distance to a given location (latitude, longitude)")
    public ResponseEntity<Object> getNearGroups(@RequestParam(value="lat", required=true) double latitude,
    											@ApiParam("Longitude of the reference location") @RequestParam(value="lon", required=true) double longitude,
    											@ApiParam("Maximum number of results") @RequestParam(value="num", required=true) int num) {
    	try {
    		
    		return ResponseEntity.ok(meetupService.getNearGroups(latitude, longitude, num));
    		
    	} catch (Exception e) {
    		
    		logger.warn("Exception getting near groups: " + e.getMessage());
    		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
    		
    	}
    }

    
    @GetMapping("/topCities")
    @ApiOperation("Returns the top num cities sorted by the number of people attending events in that city on the given date (ISO format yyyy-mm-dd)")
    public ResponseEntity<Object> getTopCities(@ApiParam("Date in ISO format YYYY-MM-DD") @RequestParam(value="date", required=true) String date,
    		                                   @ApiParam("Maximum number of results") @RequestParam(value="num", required=true) int num) {
    	try {
    		
    		return ResponseEntity.ok(meetupService.getTopCities(date, num));
    		
    	} catch (Exception e) {
    		
    		logger.warn("Exception getting top cties: " + e.getMessage());
    		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
    	}
    }

}
