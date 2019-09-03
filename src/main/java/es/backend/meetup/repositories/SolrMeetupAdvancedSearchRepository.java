package es.backend.meetup.repositories;

import java.util.Date;

import org.springframework.data.domain.Pageable;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupPage;

public interface SolrMeetupAdvancedSearchRepository {
	
    public FacetPage<RsvpDocument> findCityFacetsOnDate(Date date, int num);
    
    public GroupPage<RsvpDocument> findCitiesData(FacetPage<RsvpDocument> cities);

    public GroupPage<RsvpDocument> findEventsNearby(double latitude, double longitude, int num, Pageable pageable);

}
