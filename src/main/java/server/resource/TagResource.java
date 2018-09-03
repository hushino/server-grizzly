package server.resource;


import server.entity.Anime;
import server.entity.Tag;
import server.service.TagService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path( "/tag" )
@Produces( MediaType.APPLICATION_JSON)
@Consumes( MediaType.APPLICATION_JSON)
public class TagResource {
	private TagService tagService = new TagService();
	
	@GET
	@Path("/{animeId}")
	public Set<Tag> getAllTagsOfAnime(@PathParam( "animeId" ) Long id){
		return tagService.getAllTagsOfAnime(id);
	}
	
	/*@GET
	@Path("/s/{string}")
	public List<Tag> getAllAnimeWhithTagName(@PathParam( "string" ) String string){
		return tagService.getAllAnimeWhithTagName(string);
	}
	*/
	
	@GET
	@Path("/getAllAnimeByGenre/{id}")
	public List<Anime> getAll(@PathParam( "id" ) Long id){
		return tagService.getAll(id);
	}
}
