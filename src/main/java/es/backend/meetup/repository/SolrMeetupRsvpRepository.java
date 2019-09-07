package es.backend.meetup.repository;

import java.util.Optional;

import org.springframework.data.solr.repository.SolrCrudRepository;

import es.backend.meetup.model.RsvpDocument;

public interface SolrMeetupRsvpRepository extends SolrCrudRepository<RsvpDocument, String> {
	 
    public Optional<RsvpDocument> findById(String id);
     
}
 