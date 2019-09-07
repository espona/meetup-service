package es.backend.meetup.repository;

import java.util.Date;

import org.apache.solr.client.solrj.response.GroupResponse;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupPage;

import es.backend.meetup.model.RsvpDocument;

public interface SolrMeetupAdvancedSearchRepository {
	
    public FacetPage<RsvpDocument> findCityFacetsOnDate(Date date, int num);
    
    public GroupPage<RsvpDocument> findCitiesData(FacetPage<RsvpDocument> cities);

    public GroupResponse findGroupsNearby(double latitude, double longitude, int num);

}
