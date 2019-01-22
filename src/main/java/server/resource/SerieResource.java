package server.resource;

import server.entity.Serie;
import server.service.SerieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path( "/v1/series" )
@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_PATCH_JSON,MediaType.MULTIPART_FORM_DATA})
@Produces({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_PATCH_JSON,MediaType.MULTIPART_FORM_DATA})
public class SerieResource {
	
	private SerieService serieService = new SerieService();
	
	// get all, optionally paginated ex: /v1?start=0&size=2
	@GET
	public List<Serie> getSeries(@QueryParam( "start" ) int start,
								 @QueryParam( "size" ) int size) {
		if(start >= 0 && size>0){
			return serieService.getAllSeriesPaginated(start, size);
		}
		return serieService.getAllSeries();
	}
	
	// post in the same path
	@POST
	public Serie addSerie(Serie serie) {
		return serieService.addSerie(serie);
	}
	
	// get by id, sirve para solo mostrar la cartelera
	@GET
	@Path( "/{serieId}" )
	public List<Serie> getSerie(@PathParam( "serieId" ) Long id) {
		return serieService.getSerie(id);
	}
	
	// update requires id of an Serie
	@PATCH
	@Path( "/{serieId}" )
	public Serie updateSerie(@PathParam( "serieId" ) Long id, Serie serieid) {
		serieid.setId(id);
		return serieService.updateSerie(serieid);
	}
	
	// delete requires id of an serie, no return is need
	@DELETE
	@Path( "/{serieId}" )
	public void deleteSerie(@PathParam( "serieId" ) Long id) {
		serieService.removeSerie(id);
	}
	
	// subresource
	@Path( "/view" )
	public EpisodeResource getEpisodeResource() {
		return new EpisodeResource();
	}
	
}
