package datalayer.dataAdapter_Geocoding;

public class MapsResponse {
	private String location;
	
	private Double lat;
	
	private Double lon;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}
}
