package es.backend.meetup.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.repository.Facet;
import org.springframework.data.solr.repository.Query;

import es.backend.meetup.model.RsvpDocument;

/**
* SolrMeetupRepository combines the basic CRUD functionality and
* advanced queries on the Solr repository
* 
* @author Lucia de Espona
*
**/
public interface SolrMeetupRepository extends SolrMeetupBasicRepository, SolrMeetupAdvancedSearchRepository {

    @Query(value = "*:*") 
    @Facet(fields = { "group_city_id" }, limit = -1)
    public FacetPage<RsvpDocument> findAllCityFacets(Pageable pageable);

}
