package es.backend.meetup.dto;

import java.util.List;

import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;

@Import(BeanValidatorPluginsConfiguration.class)
public class CitiesResultDTO {
	
    private int num;
    
    private String date;
    
    private List<CityResultDTO> cities;
    
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
	
	/**
	 * @return the cities
	 */
	public List<CityResultDTO> getCities() {
		return cities;
	}
	
	/**
	 * @param cities the cities to set
	 */
	public void setCities(List<CityResultDTO> cities) {
		this.cities = cities;
	}
	
	@Override
	public String toString() {
		return "CitiesResultDTO [num=" + num + ", date=" + date + ", cities=" + cities + "]";
	}
}
