package server.resource;


import server.entity.Serie;
import server.entity.Tag;
import server.service.TagService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/tag" )
@Produces( MediaType.APPLICATION_JSON)
@Consumes({MediaType.APPLICATION_FORM_URLENCODED,MediaType.APPLICATION_JSON,MediaType.APPLICATION_JSON_PATCH_JSON,MediaType.MULTIPART_FORM_DATA})
public class TagResource {
	private TagService tagService = new TagService();
	
	@GET
	@Path("/{serieId}")
	public List<Tag> getAllTagsOfSerie(@PathParam( "serieId" ) Long id){
		return tagService.getAllTagsOfSerie(id);
	}
	
	/*@GET
	@Path("/s/{string}")
	public List<Tag> getAllAnimeWhithTagName(@PathParam( "string" ) String string){
		return tagService.getAllAnimeWhithTagName(string);
	}
	*/
	
	@GET
	@Path("/getAllSerieByGenre/{id}")
	public List<Serie> getAll(@PathParam( "id" ) Long id){
		return tagService.getAll(id);
	}
}
