package datalayer;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.Consumes;
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

import datalayer.dataCentric_Agritur.model.AgriturEntity;

@Path("/agritur")
public class AgriturResource {
	//for semplicity, using maps key as token
	private final static String apiKey = System.getenv("MAPS_KEY");
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAgritur() throws IOException {	
		System.out.println("Get all agritur...");
		List<AgriturEntity> list = AgriturEntity.getAll();
		return Response.status(Response.Status.OK).entity(list).build();		
	}
	
	@GET
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAgriturByName(@PathParam("name") String name) throws IOException {	
		System.out.println("Get agritur by name...");	
		AgriturEntity a = AgriturEntity.getById(name);
		if(a != null) {
			return Response.status(Response.Status.OK).entity(a).build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
	@PUT
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateAgritur(@PathParam("name") String name, @QueryParam("key") String key, AgriturEntity agritur) throws IOException {	
		System.out.println("Update Agritur...");
		if(
				apiKey.equals(key) && 
				name.equals(agritur.getName()) &&
				AgriturEntity.getById(name) != null
				) 
		{
			AgriturEntity newAg = AgriturEntity.saveAgritur(agritur);
			return Response.status(Response.Status.OK).entity(newAg).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@DELETE
	@Path("{name}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAgritur(@PathParam("name") String name, @QueryParam("key") String key) throws IOException {	
		System.out.println("Delete Agritur...");
		if(apiKey.equals(key)) {
			AgriturEntity toDelete = AgriturEntity.getById(name);
			return Response.status(Response.Status.OK).entity(toDelete).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveAgritur(@QueryParam("key") String key, AgriturEntity agritur) throws IOException {	
		System.out.println("Get agritur by name...");
		if(apiKey.equals(key)) {
			AgriturEntity newAg = AgriturEntity.saveAgritur(agritur);
			return Response.status(Response.Status.OK).entity(newAg).build();
		}
		return Response.status(Response.Status.BAD_REQUEST).build();
	}
}
