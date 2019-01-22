package server.resource;

import server.entity.Episode;
import server.service.HomeService;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path( "/v1" )
@Produces( MediaType.APPLICATION_JSON)
@Consumes( {MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_PATCH_JSON,MediaType.MULTIPART_FORM_DATA})
public class HomeResource {
	
	private HomeService homeService = new HomeService();

	@GET
	public List<Episode> getEpisodes(){
		return homeService.getAllEpisodesWithLimit();
	}

	/*@OPTIONS
	public Response getOptions() {
		return Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	}*/
}