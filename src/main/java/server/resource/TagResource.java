package server.resource;


import server.entity.Tag;
import server.service.TagService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/tag" )
@Produces( MediaType.APPLICATION_JSON)
@Consumes( MediaType.APPLICATION_JSON)
public class TagResource {
	private TagService tagService = new TagService();
	
	@GET
	@Path("/{animeId}")
	public List<Tag> getAllTagsOfAnime(@PathParam( "animeId" ) Long id){
		return tagService.getAllTagsOfAnime(id);
	}
	
	@GET
	@Path("/s/{string}")
	public List<Tag> getAllAnimeWhithTagName(@PathParam( "string" ) String string){
		return tagService.getAllAnimeWhithTagName(string);
	}
}
