package datalayer.dataAdapter_Weather.openWeatherMapModel;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OpenWeatherMap {
	private String cod;
	
	@JsonProperty("weather")
	private List<WeatherData> weathers;
	
	@JsonProperty("main")
	private TempData temp;

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}

	public List<WeatherData> getWeathers() {
		return weathers;
	}

	public void setWeathers(List<WeatherData> weathers) {
		this.weathers = weathers;
	}

	public TempData getTemp() {
		return temp;
	}

	public void setTemp(TempData temp) {
		this.temp = temp;
	}
}
