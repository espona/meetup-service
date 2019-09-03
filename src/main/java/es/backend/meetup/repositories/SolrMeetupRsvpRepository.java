package es.backend.meetup.repositories;

import java.util.Optional;

import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SolrMeetupRsvpRepository extends SolrCrudRepository<RsvpDocument, String> {
	 
    public Optional<RsvpDocument> findById(String id);
     
}
 