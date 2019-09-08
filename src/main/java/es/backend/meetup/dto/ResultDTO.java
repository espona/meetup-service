package es.backend.meetup.dto;

import java.util.List;

/** 
* ResultDTO is the generic object to return
* the Meetup data request results.
* 
* @author Lucia de Espona
* 
*/
public abstract class ResultDTO<T> {
	
	private List<T> results;

	private ErrorDTO error;

	/**
	 * @return the result
	 */
	public List<T> getResults() {
		return results;
	}

	/**
	 * @param result the result to set
	 */
	public void setResults(List<T> result) {
		this.results = result;
	}

	/**
	 * @return the error
	 */
	public ErrorDTO getError() {
		return error;
	}

	/**
	 * @param error the error to set
	 */
	public void setError(ErrorDTO error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "ResultDTO [results=" + results + ", error=" + error + "]";
	}

	
}
