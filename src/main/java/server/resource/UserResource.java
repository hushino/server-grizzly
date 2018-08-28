package server.resource;

import server.entity.User;
import server.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( "/login" )
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class UserResource {
	
	private UserService userService = new UserService();
	
	@GET
	@Path( "/{user}" )
	public User getUser(@PathParam( "user" ) String user) {
		return userService.getUser(user);
	}
	
	@POST
	public User addUser(User user) {
		return userService.addUser(user);
	}
	
	@POST
	@Path( "/l" )
	public boolean checkUser(@FormParam("user") String user) {
		return userService.checkUser(user);
	}
}
