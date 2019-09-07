package es.backend.meetup.dto;

//@Import(BeanValidatorPluginsConfiguration.class)
public class CitiesResultDTO extends ResultDTO<CityResultDTO> {
	
    private int num;
    
    private String date;
        
	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}
	
	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	
	@Override
	public String toString() {
		return "[" + super.toString() + " CitiesResultDTO [num=" + num + ", date=" + date + "]]";
	}
}
