/**
* SolrResultsConverter converts the repository documents from the
* query results to the dto to be returned in the requests
* 
* @author Lucia de Espona
*
**/

package es.backend.meetup.logic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.common.SolrDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.solr.core.query.result.FacetEntry;
import org.springframework.data.solr.core.query.result.FacetPage;
import org.springframework.data.solr.core.query.result.GroupEntry;
import org.springframework.data.solr.core.query.result.GroupPage;
import org.springframework.stereotype.Component;

import es.backend.meetup.dto.CityDTO;
import es.backend.meetup.dto.CityResultDTO;
import es.backend.meetup.dto.GroupDTO;
import es.backend.meetup.dto.GroupResultDTO;
import es.backend.meetup.model.RsvpDocument;

@Component
public class SolrResultsConverter {
	
	public List<GroupResultDTO> solrGroupResult2GroupDtoList(GroupResponse groupResponse) {
	
		List<GroupResultDTO> groupsResultList = new ArrayList<GroupResultDTO>();

		if (groupResponse != null) {
			
			for (GroupCommand groupCommand : groupResponse.getValues()) {
				
				for (Group group : groupCommand.getValues()) { 
					
					if (!group.getResult().isEmpty()) {
						
						// We only need the first document of each group to get the meetup group data
						SolrDocument document = group.getResult().get(0);
						GroupDTO dto = solrDocument2GroupDto(document);
						
						GroupResultDTO resultDto = new GroupResultDTO();
						resultDto.setDistance((Float) document.getFieldValue("distance"));
						resultDto.setGroup(dto);
	
						groupsResultList.add(resultDto);
					}
				}
			}
		}
		return groupsResultList;
	}
	
	@SuppressWarnings("unchecked")
	public List<CityResultDTO> solrResults2CityDtoList (FacetPage<RsvpDocument> facetPageCities, GroupPage<RsvpDocument> groupPageCities){
		
		List<CityResultDTO> cityResultList = new ArrayList<CityResultDTO>();

		// fill up a map of city ids -> RsvpDocument containing the city data
		HashMap<String, RsvpDocument> cityData = new HashMap<String, RsvpDocument>(); 
		
		List<GroupEntry<RsvpDocument>>  cityGroupEntries = groupPageCities.getGroupResult("group_city_id").getGroupEntries().getContent();			
		Iterator<GroupEntry<RsvpDocument>> groupListIterator = cityGroupEntries.iterator();
		
		while (groupListIterator.hasNext()) {
			
			GroupEntry<RsvpDocument> groupEntry = groupListIterator.next();
			if (!groupEntry.getResult().getContent().isEmpty()) {
				
				// We only need the first document of each group to get the meetup group data
				RsvpDocument document = groupEntry.getResult().getContent().get(0);
				cityData.put(document.getGroupCityId(), document);
			}
		}
		
		// add the facet list (ordered) results and include the data from the group query
		Collection<Page<? extends FacetEntry>> facets = facetPageCities.getAllFacets();		
		Iterator<Page<? extends FacetEntry>> iterator = facets.iterator();
		
		while (iterator.hasNext()) {

			List<FacetEntry> facetList = (List<FacetEntry>) iterator.next().getContent();

			Iterator<FacetEntry> facetListIterator = facetList.iterator();
			while (facetListIterator.hasNext()) {
				
				FacetEntry facetEntry = facetListIterator.next();
				String cityId = facetEntry.getValue();
				Long count = facetEntry.getValueCount();
				
				if (cityData.containsKey(cityId)) {
					
					CityDTO dto = rsvpDocument2CityDto(cityData.get(cityId));
					
					CityResultDTO resultDto = new CityResultDTO();
					resultDto.setEvents(count);
					resultDto.setCity(dto);

					cityResultList.add(resultDto);
				}
			}
		}
		
		return cityResultList;
	}
	
	private CityDTO rsvpDocument2CityDto (RsvpDocument document) {
				
		CityDTO dto = new CityDTO();
		dto.setId(document.getGroupCityId());
		dto.setCountry(document.getGroupCountry());
		dto.setState(document.getGroupState());
		dto.setName(document.getGroupCity());
		
		return dto;
	}
	
	private GroupDTO solrDocument2GroupDto(SolrDocument document) {
		
		GroupDTO dto = new GroupDTO();
		
		dto.setGroup_id((String) document.getFieldValue("group_id"));
		dto.setGroup_name((String) document.getFieldValue("group_name"));
		dto.setGroup_country((String) document.getFieldValue("group_country"));
		dto.setGroup_state((String) document.getFieldValue("group_state"));
		dto.setGroup_city((String) document.getFieldValue("group_city"));
		dto.setGroup_lat((Double) document.getFieldValue("group_lat"));
		dto.setGroup_lon((Double) document.getFieldValue("group_lon"));
		dto.setGroup_urlname((String) document.getFieldValue("group_urlname"));
		
		return dto;
	}

}
