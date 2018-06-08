package datalayer;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datalayer.dataAdapter_Geocoding.GoogleMapsHandler;
import datalayer.dataAdapter_Geocoding.MapsResponse;

@Path("/geocoding")
public class GeocodingResource {
	
	@GET
	@Path("{key}/{location}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPerson(@PathParam("key") String key, @PathParam("location") String location) throws IOException {	
		System.out.println("Get coordinates...");
		if( ! GoogleMapsHandler.acceptKey(key)) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		MapsResponse result = GoogleMapsHandler.sendRequest(location);
		if(result == null) return Response.status(Response.Status.NOT_FOUND).build();
		return Response.status(Response.Status.OK).entity(result).build();
}
}
