package server.provider;

import com.google.gson.Gson;
import org.glassfish.jersey.internal.util.Base64;
import server.service.UserService;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {
	
	private static final String AUTHOTIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "v1/";
	//private static final String SECURED_URL_PREFIX = "login/check";
	private static final String RESPONSE_FAIL = "User cannot access the resource.";

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if(requestContext.getUriInfo().getPath().contains(SECURED_URL_PREFIX)){
            List<String> authHeader = requestContext.getHeaders().get(AUTHOTIZATION_HEADER_KEY);
            if( authHeader != null && authHeader.size()>0){
                String authToken = authHeader.get(0);
                authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                String decodedString = Base64.decodeAsString(authToken);
                StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                String username = tokenizer.nextToken();
                String password = tokenizer.nextToken();
				// retirar esto y comprobar a parte si es que existe el usuario en la BD
				// luego simplemente aplicar el token en caso de que exista
                if("hus".equals(username) && "pas".equals(password)){
                    return;
                }
            }
			Gson g = new Gson();
			String str = g.toJson(RESPONSE_FAIL);
			
			Response unauthorizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED)
                    .entity(str)
                    .build();

			requestContext.abortWith(unauthorizedStatus);
		}
	}
	
	/*private void login(String user){
        UserService userService=new UserService();
        userService.checkUser(user);
	}

	private boolean asd(ContainerRequestContext requestContext){
        List<String> authHeader = requestContext.getHeaders().get(AUTHOTIZATION_HEADER_KEY);
        if( authHeader != null && authHeader.size()>0){
            String authToken = authHeader.get(0);
            authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
            String decodedString = Base64.decodeAsString(authToken);
            StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
            String username = tokenizer.nextToken();
            String password = tokenizer.nextToken();

            if("user".equals(username) && "password".equals(password)){
                return true;
                //return;
            }
        }
        return false;
    }*/
}
