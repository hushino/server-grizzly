package server.resource;


import server.entity.Serie;
import server.entity.Episode;
import server.service.EpisodeService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/" )
@Produces( MediaType.APPLICATION_JSON )
@Consumes( {MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_PATCH_JSON,MediaType.MULTIPART_FORM_DATA} )
public class EpisodeResource {
	
	private EpisodeService episodeService = new EpisodeService();
	
	@GET
	@Path( "/{serieId}" )
	public List<Episode> getAllEpisodesOfAnAnime(@PathParam( "serieId" ) Long serieId) {
		return episodeService.getAllEpisodesOfAnSerie(serieId);
	}
	
	@GET
	@Path( "/{serieId}/{episodeId}" )
	public List<Episode> getUniqueEpisodesOfAnime(@PathParam( "serieId" ) Long serieId,@PathParam( "episodeId" ) Long episodeId) {
		return episodeService.getUniqueEpisodesOfSerie(serieId,episodeId);
	}
	
	@GET
	@Path( "/{serieId}/v" )
	public Serie getAnimeOfAnEpisode(@PathParam( "serieId" ) Long serieId) {
		return episodeService.getSerieOfAnEpisode(serieId);
	}
	
	@POST
	@Path( "/{serieId}" )
	public Episode addEpisode(@PathParam( "serieId" ) Long serieId, Episode episode) {
		return episodeService.addEpisode(serieId, episode);
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

