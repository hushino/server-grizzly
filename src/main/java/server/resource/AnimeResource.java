package server.resource;

import server.entity.Anime;
import server.service.AnimeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path( "/v1/animes" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class AnimeResource {
	
	private AnimeService animeService = new AnimeService();
	
	// get all, optionally paginated ex: /v1?start=0&size=2
	@GET
	public List<Anime> getAnimes(@QueryParam( "start" ) int start,
	                             @QueryParam( "size" ) int size) {
		if(start >= 0 && size>0){
			return animeService.getAllAnimesPaginated(start, size);
		}
		return animeService.getAllAnimes();
	}
	
	// post in the same path
	@POST
	public Anime addAnime(Anime anime) {
		return animeService.addAnime(anime);
	}
	
	// get by id, sirve para solo mostrar la cartelera
	@GET
	@Path( "/{animeId}" )
	public Anime getAnime(@PathParam( "animeId" ) Long id) {
		return animeService.getAnime(id);
	}
	
	// update requires id of an anime
	@PUT
	@Path( "/{animeId}" )
	public Anime updateAnime(@PathParam( "animeId" ) Long id, Anime animeid) {
		animeid.setId(id);
		return animeService.updateAnime(animeid);
	}
	
	// delete requires id of an anime, no return is need
	@DELETE
	@Path( "/{animeId}" )
	public void deleteAnime(@PathParam( "animeId" ) Long id) {
		animeService.removeAnime(id);
	}
	
	// subresource
	@Path( "/view" )
	public EpisodeResource getEpisodeResource() {
		return new EpisodeResource();
	}
	
}
