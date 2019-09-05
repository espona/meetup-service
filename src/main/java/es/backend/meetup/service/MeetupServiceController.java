package es.backend.meetup.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RequestMapping("/meetup")
@RestController
public class MeetupServiceController {
    
	Logger logger = LoggerFactory.getLogger(MeetupServiceController.class);
		
	@Autowired
	MeetupService meetupService;

    @RequestMapping("/near")
    public ResponseEntity<Object> getNearGroups(@RequestParam(value="lat", required=true) double latitude,
    											@RequestParam(value="lon", required=true) double longitude,
    											@RequestParam(value="num", defaultValue = "5") int num) {
    	try {
    		
    		return ResponseEntity.ok(meetupService.getNearGroups(latitude, longitude, num));
    		
    	} catch (Exception e) {
    		
    		logger.warn("Exception getting near groups: " + e.getMessage());
    		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
    		
    	}
    }

    @RequestMapping("/topCities")
    public ResponseEntity<Object> getTopCities(@RequestParam(value="date", required=true) String date,
    									 	   @RequestParam(value="num", defaultValue = "5") int num) {
    	try {
    		
    		return ResponseEntity.ok(meetupService.getTopCities(date, num));
    		
    	} catch (Exception e) {
    		
    		logger.warn("Exception getting top cties: " + e.getMessage());
    		return ResponseEntity.status(500).body("Internal Server Error: " + e.getLocalizedMessage());
    	}
    }

}
