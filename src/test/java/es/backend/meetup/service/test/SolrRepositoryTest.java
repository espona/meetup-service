package es.backend.meetup.service.test;

import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.dao.DataAccessResourceFailureException;
import es.backend.meetup.model.RsvpDocument;
import es.backend.meetup.repository.SolrMeetupRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class SolrRepositoryTest {

	@Autowired
	SolrMeetupRepository repo;
	
	@After
	public void tearDown() {
		repo.deleteById(createRsvpDocument().getId());
	}

	@Test
	public void testQueryAll() {
		
	    long totalCount = repo.count();	    
		Page<RsvpDocument> resultPage = repo.findAll(PageRequest.of(0, 10));
		Assert.assertEquals(totalCount, resultPage.getTotalElements());
	}
	
	@Test
	public void testSaveAndFindRsvp() {
				
		RsvpDocument documentInitial = createRsvpDocument();
		repo.save(documentInitial);
		
		RsvpDocument documentRetrieved = repo.findById(documentInitial.getId()).get();
		Assert.assertEquals(documentInitial.getGroupName(), documentRetrieved.getGroupName());
	}
	
	@Test(expected=DataAccessResourceFailureException.class)
	public void insertNullIdFails() {
		RsvpDocument documentNullId = createRsvpDocument();
		documentNullId.setId(null);
		repo.save(documentNullId);
	}
	
	protected RsvpDocument createRsvpDocument() {
		String id = "test-document-to-delete";
		RsvpDocument document = new RsvpDocument();
    	document.setId(id);
    	document.setRsvpMtime(new Date().getTime());
    	document.setResponse(true);
    	document.setGuests(0);
    	document.setEventId(id + "_event");
    	document.setEventName("Test Event");
    	document.setEventUrl("http://test.event.com");
    	document.setEventTime(new Date().getTime());
    	document.setEventDate(new Date());
    	document.setGroupId(id + "_group");
    	document.setGroupName("Test Group");
    	document.setGroupUrlName("http://test.group.com");
    	document.setGroupCountry("ES");
    	document.setGroupState(null);
    	document.setGroupCity("Test City");
    	document.setGroupCityId(document.generateCityId());;	
    	document.setGroupLat(43.25);
    	document.setGroupLon(-8.34);
    	document.setGroupPosition(document.generateGroupPosition());
    	document.setMemberId(id + "_member");
    	document.setMemberName("Test Member");
    	document.setMemberPhoto(null);
		document.setVenueId(id + "_venue");
		document.setVenueName("Test venue");
		document.setVenueLat(43.25);
		document.setVenueLon(-8.34);
		return document;
	}
	
}
