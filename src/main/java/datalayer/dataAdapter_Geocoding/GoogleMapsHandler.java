package datalayer.dataAdapter_Geocoding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datalayer.dataAdapter_Geocoding.googleMapsModel.GoogleMaps;

public class GoogleMapsHandler {
	
	private final static String endpoint = "https://maps.googleapis.com/maps/api/geocode/json?address=";
	
	private final static String apiKey = System.getenv("MAPS_KEY");

	public static MapsResponse sendRequest(String location) throws IOException {
		String getInput = endpoint + location + "&key=" + apiKey;
		String getOutput = doGet(getInput);
		
		GoogleMaps googleMaps = unmarshall(getOutput);
		
		if(! googleMaps.getStatus().equals("OK") ) {
			System.out.println("MAPS JSON: " + getOutput);
			return null;
		}
		
		return makeResponse(googleMaps);
	}

	public static boolean acceptKey(String key) {
		if(key.equals(apiKey)) return true;
		return false;
	}
	
	private static String doGet(String url) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		return response.toString();
	}

	private static GoogleMaps unmarshall(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //this will ignore all other attributes not mapped
		GoogleMaps googleMaps = mapper.readValue(json, GoogleMaps.class);
		return googleMaps;
	}
	
	private static MapsResponse makeResponse(GoogleMaps googleMaps) {
		MapsResponse response = new MapsResponse();
		response.setLat(googleMaps.getResults().get(0).getGeometry().getLocation().getLat());
		response.setLon(googleMaps.getResults().get(0).getGeometry().getLocation().getLng());
		return response;
	}
}
