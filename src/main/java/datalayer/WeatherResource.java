package datalayer;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datalayer.dataAdapter_Weather.OpenWeatherMapHandler;
import datalayer.dataAdapter_Weather.WeatherResponse;

@Path("/weather")
public class WeatherResource {
	@GET
	@Path("{key}/{lat}/{lon}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("key") String key, @PathParam("lat") Double lat, @PathParam("lon") Double lon) throws IOException {	
		System.out.println("Get weather...");
		if( ! OpenWeatherMapHandler.acceptKey(key)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		WeatherResponse result = OpenWeatherMapHandler.sendRequest(lat, lon);
		if(result == null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(result).build();
	}
}
