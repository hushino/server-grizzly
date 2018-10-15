package server.resource;

import server.entity.User;
import server.service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

    /*@GET
    @Path("/test1")
    public Response createCookies() {
        NewCookie cookie1 = new NewCookie("myStrCookie", "cookieStrVal");
        NewCookie cookie2 = new NewCookie("myDateCookie", "2017-03-28");
        NewCookie cookie3 = new NewCookie("myIntCookie", "100");
        Response.ResponseBuilder rb = Response.ok("myStrCookie, "
                + "myDateCookie and myIntCookie sent to the browser");
        Response response = rb.cookie(cookie1, cookie2, cookie3)
                .build();
        return response;
    }*/
    @POST
	@Path( "/check" )
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean checkUser(@FormParam("user")String username/*, @FormParam("pass")String password*/) {
		return userService.checkUser(username);
    }
	/*@POST
	@Path( "/check" )
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public boolean checkUser(@FormParam("user") String user) {
		return userService.checkUser(user);
	}*/

	/*@POST
	@Path( "/check" )
	public boolean checkUser(@FormParam("user") String user) {
		return userService.checkUser(user);
	}*/
}
