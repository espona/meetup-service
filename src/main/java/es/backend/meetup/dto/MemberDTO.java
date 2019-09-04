package es.backend.meetup.dto;

public class MemberDTO {
	
	private long member_id;
	
	private String photo;
	
	private String member_name;
	
	/**
	 * @return the member_id
	 */
	public long getMember_id() {
		return member_id;
	}
	
	/**
	 * @param member_id the member_id to set
	 */
	public void setMember_id(long member_id) {
		this.member_id = member_id;
	}
	
	/**
	 * @return the photo
	 */
	public String getPhoto() {
		return photo;
	}
	
	/**
	 * @param photo the photo to set
	 */
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	/**
	 * @return the member_name
	 */
	public String getMember_name() {
		return member_name;
	}
	
	/**
	 * @param member_name the member_name to set
	 */
	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}
	
	@Override
	public String toString() {
		return "MemberObject [member_id=" + member_id + ", member_name=" + member_name + ", photo=" + photo + "]";
	}
}
