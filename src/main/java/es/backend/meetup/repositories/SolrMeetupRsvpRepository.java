package es.backend.meetup.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.repository.Query;
import org.springframework.data.solr.repository.SolrCrudRepository;

public interface SolrMeetupRsvpRepository extends SolrCrudRepository<RsvpDocument, String> {
	 
    public Optional<RsvpDocument> findById(String id);
 
    @Query("id:*?0* OR name:*?0*")
    public Page<RsvpDocument> findByCustomQuery(String searchTerm, Pageable pageable);
 
    //@Query(name = "Product.findByNamedQuery")
    //public Page<RsvpDocument> findByNamedQuery(String searchTerm, Pageable pageable);
}
 