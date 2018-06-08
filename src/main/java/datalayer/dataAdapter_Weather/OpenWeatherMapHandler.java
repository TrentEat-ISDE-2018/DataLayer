package datalayer.dataAdapter_Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import datalayer.dataAdapter_Geocoding.MapsResponse;
import datalayer.dataAdapter_Geocoding.googleMapsModel.GoogleMaps;
import datalayer.dataAdapter_Weather.openWeatherMapModel.OpenWeatherMap;
import datalayer.dataAdapter_Weather.openWeatherMapModel.WeatherData;

public class OpenWeatherMapHandler {
	
	private final static String endpoint = "http://api.openweathermap.org/data/2.5/weather?";
	
	private final static String apiKey = System.getenv("OPENWEATHERMAP_KEY");

	public static WeatherResponse sendRequest(Double lat, Double lon) throws IOException {
		String getInput = endpoint + "lat=" + lat + "&lon=" + lon + "&APPID=" + apiKey;
		String getOutput = doGet(getInput);

		OpenWeatherMap openWeatherMap= unmarshall(getOutput);
		
		WeatherResponse response = makeResponse(openWeatherMap);
		if(response == null) {
			System.out.println("OPENWEATHERMAP JSON: " + getOutput);
		}
		return response;
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
	
	private static OpenWeatherMap unmarshall(String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //this will ignore all other attributes not mapped
		OpenWeatherMap openWeatherMap = mapper.readValue(json, OpenWeatherMap.class);
		return openWeatherMap;
	}
	
	public static WeatherResponse makeResponse(OpenWeatherMap owm) {
		WeatherResponse response = new WeatherResponse();
		if(! owm.getCod().equals("200")) {
			System.out.println("Code");
			return null;
		}
		List<WeatherData> wdList = owm.getWeathers();
		if(wdList.isEmpty()) {
			System.out.println("List");
			return null;
		}
		String description = "";
		String main = "";
		for(WeatherData wd : wdList) {
			main += " " + wd.getMain();
			description += " " + wd.getDescription();
		}
		WeatherData wd = wdList.get(0);
		response.setId(wd.getId());
		response.setMain(main);
		response.setDescription(description);
		response.setTemp(owm.getTemp().getTemp() - 273.15);
		response.setTemp_max(owm.getTemp().getTemp_max() - 273.15);
		response.setTemp_min(owm.getTemp().getTemp_min() - 273.15);
		return response;
	}

}
