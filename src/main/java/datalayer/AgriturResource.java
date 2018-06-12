package datalayer;

import java.io.IOException;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import datalayer.dataAdapter_Geocoding.GoogleMapsHandler;
import datalayer.dataAdapter_Geocoding.MapsResponse;
import datalayer.dataCentric_Agritur.model.AgriturEntity;

@Path("/agritur")
public class AgriturResource {
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAgritur(@QueryParam("lat") String lat, @QueryParam("lon") String lon) throws IOException {	
		System.out.println("Get all agritur...");
		
		return Response.status(Response.Status.OK).entity(null).build();
	}
	
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAgriturByName(@PathParam("name") String name) throws IOException {	
		System.out.println("Get agritur by name...");
		
		return Response.status(Response.Status.OK).entity(null).build();
	}
	
	@PUT
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAgritur(@PathParam("name") String name, @QueryParam("key") String key, AgriturEntity agritur) throws IOException {	
		System.out.println("Update Agritur...");
		
		return Response.status(Response.Status.OK).entity(null).build();
	}
	
	@DELETE
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAgritur(@PathParam("name") String name, @QueryParam("key") String key) throws IOException {	
		System.out.println("Delete Agritur...");
		
		return Response.status(Response.Status.OK).entity(null).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveAgritur(AgriturEntity agritur) throws IOException {	
		System.out.println("Get agritur by name...");
		
		return Response.status(Response.Status.OK).entity(null).build();
	}
}
