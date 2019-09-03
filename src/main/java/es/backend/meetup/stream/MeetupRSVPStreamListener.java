package es.backend.meetup.stream;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import es.backend.meetup.dto.RsvpDTO;
import es.backend.meetup.stream.event.MeetupStreamEventPublisher;

@Component
public class MeetupRSVPStreamListener  {

	private boolean isListening = false;
	private boolean shouldStopListening = false;
	private int receivedRsvps = 0;
	
	@Autowired
	private MeetupStreamEventPublisher publisher;
	
	@Value("${meetup.stream.url}")
	private String streamUrl;

	
    private static final Logger logger = LoggerFactory.getLogger(MeetupRSVPStreamListener.class);
        
    public void setStreamUrl(String url) {
    	this.streamUrl = url;
    }
    
    public void startListening() {
    
    	this.shouldStopListening = false;
    	if(this.isListening) {
    		logger.info("Already listening");
    	} else {
        	this.isListening = true;
    		logger.info("Start Listening (" + this.streamUrl + ")...");
    		this.listen();
    	}
    }
    
    public void stopListening() {
		logger.info("STOP Listening [" + this.receivedRsvps + "]...");
    	this.shouldStopListening = true;
    }
    
    private void listen() {
    	
    	this.isListening = true;
    			    	
    	while(!this.shouldStopListening) {
    		try {
    			int currentCount = this.receivedRsvps;
				CompletableFuture<RsvpDTO> futureRsvp = getRsvp();
	    		logger.info("Rsvp [" + currentCount + "]: " + futureRsvp.get().toString());
	    		this.receivedRsvps += 1;
	    		this.publisher.publishRsvpReceivedEvent(futureRsvp.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
				logger.error(e.getMessage() + ": " + e.toString());
			}  catch (ExecutionException e) {
				e.printStackTrace();
				logger.error(e.getMessage() + ": " + e.toString());
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Unexpected exception, STOPPING:" + e.getMessage() + ", " + e.toString());
				this.shouldStopListening = true;
			}
    	}
    	
    	this.isListening = false;
    	
    }
    
    public CompletableFuture<RsvpDTO> getRsvp() throws InterruptedException {
    	
        RestTemplate restTemplate = new RestTemplate();
        RsvpDTO rsvpObject = restTemplate.getForObject(this.streamUrl, RsvpDTO.class);
        // test purposes
        Thread.sleep(1000);
        
    	return CompletableFuture.completedFuture(rsvpObject);
    }

}
