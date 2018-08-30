package server.resource;


import server.entity.Anime;
import server.entity.Episode;
import server.service.EpisodeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;

@Path( "/" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( MediaType.APPLICATION_JSON )
public class EpisodeResource {
	
	private EpisodeService episodeService = new EpisodeService();
	
	@GET
	@Path( "/{animeId}" )
	public Set<Episode> getAllEpisodesOfAnAnime(@PathParam( "animeId" ) Long animeId) {
		return episodeService.getAllEpisodesOfAnAnime(animeId);
	}
	
	@GET
	@Path( "/{animeId}/{episodeId}" )
	public Set<Episode> getAllEpisodesOfAnAnime(@PathParam( "animeId" ) Long animeId,@PathParam( "episodeId" ) Long episodeId) {
		return episodeService.getEpisodesOfAnAnime(animeId,episodeId);
	}
	
	@GET
	@Path( "/{animeId}/v" )
	public Anime getAnimeOfAnEpisode(@PathParam( "animeId" ) Long animeId) {
		return episodeService.getAnimeOfAnEpisode(animeId);
	}
	
	@POST
	@Path( "/{animeId}" )
	public Episode addEpisode(@PathParam( "animeId" ) Long animeId, Episode episode) {
		return episodeService.addEpisode(animeId, episode);
	}
	
	@PUT
	@Path( "/{episodeId}" )
	public Episode putEpisode(@PathParam( "episodeId" ) Long episodeId, Episode episode) {
		episode.setId(episodeId); // id del episodio a actualizar
		return episodeService.putEpisode(episode);
	}
	
	@DELETE
	@Path( "/{episodeId}" )
	public void deleteEpisode(@PathParam( "episodeId" ) Long episodeId) {
		episodeService.removeEpisode(episodeId);
	}
}

