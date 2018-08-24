package server.resource;

import server.entity.Episode;
import server.service.HomeService;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/v1" )
@Produces( MediaType.APPLICATION_JSON)
@Consumes( MediaType.APPLICATION_JSON)
public class HomeResource {
	
	private HomeService homeService = new HomeService();
	
	@GET
	public List<Episode> getEpisodes(){
		return homeService.getAllEpisodesWithLimit();
	}
}