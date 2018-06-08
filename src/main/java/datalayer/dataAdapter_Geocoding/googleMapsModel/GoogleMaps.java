package datalayer.dataAdapter_Geocoding.googleMapsModel;

import java.util.List;

public class GoogleMaps {
	
	List<Address_components> results;
	
	private String status;
	
	public List<Address_components> getResults() {
		return results;
	}
	public void setResults(List<Address_components> results) {
		this.results = results;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
