/**
* RsvpDocument is the document containing the RSVP data
* stored in the Solr repository
* 
* @author Lucia de Espona
*
**/

package es.backend.meetup.model;

import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.Score;
import org.springframework.data.solr.core.mapping.SolrDocument;

@SolrDocument(collection = "item_meetup_rsvp")
public class RsvpDocument {
	
	@Id
    @Indexed(name = "id")
    private String id;
 
	@Indexed(name = "rsvp_mtime")
	private Long rsvpMtime;
	   
	@Indexed(name = "response")
	private Boolean response;

	@Indexed(name = "guests")
	private Integer guests;

	// Event fields
	@Indexed(name = "event_id")
    private String eventId;

	@Indexed(name = "event_name")
    private String eventName;

	@Indexed(name = "event_url")
    private String eventUrl;

	@Indexed(name = "event_time")
    private Long eventTime;

	@Indexed(name = "event_date")
	private Date eventDate;

	// Group Fields
	@Indexed(name = "group_id")
    private String groupId;

    @Indexed(name = "group_name")
    private String groupName;

    @Indexed(name = "group_urlname")
    private String groupUrlName;

    @Indexed(name = "group_country")
    private String groupCountry;

    @Indexed(name = "group_state")
    private String groupState;

    @Indexed(name = "group_city")
    private String groupCity;

    @Indexed(name = "group_city_id")
    private String groupCityId;

    @Indexed(name = "group_topic_name")
    private List<String> groupTopicName;

    @Indexed(name = "group_topic_urlkey")
    private List<String> groupTopicUrlkey;

	@Indexed(name = "group_lat")
    private Double groupLat;

    @Indexed(name = "group_lon")
    private Double groupLon;
    
    @Indexed(name = "position") 
    private String groupPosition; // GPS location "POINT (lon, lat)", for example "POINT (-8.3776545 43.3362)"

    // Member Fields
    @Indexed(name = "member_id")
    private String memberId;
    
    @Indexed(name = "member_name")
    private String memberName;
    
    @Indexed(name = "member_other_services")
    private String memberOtherServices;    
    
    @Indexed(name = "member_photo")
    private String memberPhoto;    
    
    // Venue Fields
    @Indexed(name = "venue_id")
    private String venueId;
    
    @Indexed(name = "venue_name")
    private String venueName;
    
	@Indexed(name = "venue_lat")
    private Double venueLat;

    @Indexed(name = "venue_lon")
    private Double venueLon;

    //Calculated fields (do not index)
  	
    @Field
  	private Float distance; // Distance in km for spatial queries
  	
    @Score
    private Float score;



	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the rsvpMtime
	 */
	public Long getRsvpMtime() {
		return rsvpMtime;
	}

	/**
	 * @param rsvpMtime the rsvpMtime to set
	 */
	public void setRsvpMtime(Long rsvpMtime) {
		this.rsvpMtime = rsvpMtime;
	}

	/**
	 * @return the response
	 */
	public Boolean getResponse() {
		return response;
	}

	/**
	 * @param response the response to set
	 */
	public void setResponse(Boolean response) {
		this.response = response;
	}

	/**
	 * @return the guests
	 */
	public Integer getGuests() {
		return guests;
	}

	/**
	 * @param guests the guests to set
	 */
	public void setGuests(Integer guests) {
		this.guests = guests;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * @return the eventUrl
	 */
	public String getEventUrl() {
		return eventUrl;
	}

	/**
	 * @param eventUrl the eventUrl to set
	 */
	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}

	/**
	 * @return the eventTime
	 */
	public Long getEventTime() {
		return eventTime;
	}

	/**
	 * @param eventTime the eventTime to set
	 */
	public void setEventTime(Long eventTime) {
		this.eventTime = eventTime;
	}

	/**
	 * @return the eventDate
	 */
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the groupUrlName
	 */
	public String getGroupUrlName() {
		return groupUrlName;
	}

	/**
	 * @param groupUrlName the groupUrlName to set
	 */
	public void setGroupUrlName(String groupUrlName) {
		this.groupUrlName = groupUrlName;
	}

	/**
	 * @return the groupCountry
	 */
	public String getGroupCountry() {
		return groupCountry;
	}

	/**
	 * @param groupCountry the groupCountry to set
	 */
	public void setGroupCountry(String groupCountry) {
		this.groupCountry = groupCountry;
	}

	/**
	 * @return the groupState
	 */
	public String getGroupState() {
		return groupState;
	}

	/**
	 * @param groupState the groupState to set
	 */
	public void setGroupState(String groupState) {
		this.groupState = groupState;
	}

	/**
	 * @return the groupCity
	 */
	public String getGroupCity() {
		return groupCity;
	}

	/**
	 * @param groupCity the groupCity to set
	 */
	public void setGroupCity(String groupCity) {
		this.groupCity = groupCity;
	}

	/**
	 * @return the groupCityId
	 */
	public String getGroupCityId() {
		return groupCityId;
	}

	/**
	 * @param groupCityId the groupCityId to set
	 */
	public void setGroupCityId(String groupCityId) {
		this.groupCityId = groupCityId;
	}

	/**
	 * @return the groupTopicName
	 */
	public List<String> getGroupTopicName() {
		return groupTopicName;
	}

	/**
	 * @param groupTopicName the groupTopicName to set
	 */
	public void setGroupTopicName(List<String> groupTopicName) {
		this.groupTopicName = groupTopicName;
	}

	/**
	 * @return the groupTopicUrlkey
	 */
	public List<String> getGroupTopicUrlkey() {
		return groupTopicUrlkey;
	}

	/**
	 * @param groupTopicUrlkey the groupTopicUrlkey to set
	 */
	public void setGroupTopicUrlkey(List<String> groupTopicUrlkey) {
		this.groupTopicUrlkey = groupTopicUrlkey;
	}

	/**
	 * @return the groupLat
	 */
	public Double getGroupLat() {
		return groupLat;
	}

	/**
	 * @param groupLat the groupLat to set
	 */
	public void setGroupLat(Double groupLat) {
		this.groupLat = groupLat;
	}

	/**
	 * @return the groupLon
	 */
	public Double getGroupLon() {
		return groupLon;
	}

	/**
	 * @param groupLon the groupLon to set
	 */
	public void setGroupLon(Double groupLon) {
		this.groupLon = groupLon;
	}

	/**
	 * @return the groupPosition
	 */
	public String getGroupPosition() {
		return groupPosition;
	}

	/**
	 * @param groupPosition the groupPosition to set
	 */
	public void setGroupPosition(String groupPosition) {
		this.groupPosition = groupPosition;
	}

	/**
	 * @return the memberId
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId the memberId to set
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	/**
	 * @return the memberName
	 */
	public String getMemberName() {
		return memberName;
	}

	/**
	 * @param memberName the memberName to set
	 */
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	/**
	 * @return the memberOtherServices
	 */
	public String getMemberOtherServices() {
		return memberOtherServices;
	}

	/**
	 * @param memberOtherServices the memberOtherServices to set
	 */
	public void setMemberOtherServices(String memberOtherServices) {
		this.memberOtherServices = memberOtherServices;
	}

	/**
	 * @return the memberPhoto
	 */
	public String getMemberPhoto() {
		return memberPhoto;
	}

	/**
	 * @param memberPhoto the memberPhoto to set
	 */
	public void setMemberPhoto(String memberPhoto) {
		this.memberPhoto = memberPhoto;
	}

	/**
	 * @return the venueId
	 */
	public String getVenueId() {
		return venueId;
	}

	/**
	 * @param venueId the venueId to set
	 */
	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	/**
	 * @return the venueName
	 */
	public String getVenueName() {
		return venueName;
	}

	/**
	 * @param venueName the venueName to set
	 */
	public void setVenueName(String venueName) {
		this.venueName = venueName;
	}

	/**
	 * @return the venueLat
	 */
	public Double getVenueLat() {
		return venueLat;
	}

	/**
	 * @param venueLat the venueLat to set
	 */
	public void setVenueLat(Double venueLat) {
		this.venueLat = venueLat;
	}

	/**
	 * @return the venueLon
	 */
	public Double getVenueLon() {
		return venueLon;
	}

	/**
	 * @param venueLon the venueLon to set
	 */
	public void setVenueLon(Double venueLon) {
		this.venueLon = venueLon;
	}

	/**
	 * @return the distance
	 */
	public Float getDistance() {
		return distance;
	}

	/**
	 * @param distance the distance to set
	 */
	public void setDistance(Float distance) {
		this.distance = distance;
	}

	/**
	 * @return the score
	 */
	public Float getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Float score) {
		this.score = score;
	}

	// Generate the city identifier 
	public String generateCityId() {
		
		String cityId = this.groupCountry;
		
		if (this.groupState != null) {
			cityId += "_" + this.groupState;
		}
		
		cityId += "_" + this.groupCity;

		return cityId.toLowerCase(); 
	}
	
	// Generate the position as rpt
	public String generateGroupPosition() {
		
		return "POINT (" + this.groupLon + " " + this.groupLat + ")";
	}

	@Override
	public String toString() {
		return "RsvpDocument [id=" + id + ", rsvpMtime=" + rsvpMtime + ", response=" + response + ", guests=" + guests
				+ ", eventId=" + eventId + ", eventName=" + eventName + ", eventUrl=" + eventUrl + ", eventTime="
				+ eventTime + ", eventDate=" + eventDate + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", groupUrlName=" + groupUrlName + ", groupCountry=" + groupCountry + ", groupState=" + groupState
				+ ", groupCity=" + groupCity + ", groupCityId=" + groupCityId + ", groupTopicName=" + groupTopicName
				+ ", groupTopicUrlkey=" + groupTopicUrlkey + ", groupLat=" + groupLat + ", groupLon=" + groupLon
				+ ", groupPosition=" + groupPosition + ", memberId=" + memberId + ", memberName=" + memberName
				+ ", memberOtherServices=" + memberOtherServices + ", memberPhoto=" + memberPhoto + ", venueId="
				+ venueId + ", venueName=" + venueName + ", venueLat=" + venueLat + ", venueLon=" + venueLon
				+ ", distance=" + distance + ", score=" + score + "]";
	}

}
