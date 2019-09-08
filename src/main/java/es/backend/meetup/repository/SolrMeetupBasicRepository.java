package es.backend.meetup.repository;

import java.util.Optional;

import org.springframework.data.solr.repository.SolrCrudRepository;

import es.backend.meetup.model.RsvpDocument;

/**
* SolrMeetupBasicRepository defines
* basic CRUD operations on the Solr repository
* 
* @author Lucia de Espona
*
**/
public interface SolrMeetupBasicRepository extends SolrCrudRepository<RsvpDocument, String> {
	 
    public Optional<RsvpDocument> findById(String id);
     
}
 