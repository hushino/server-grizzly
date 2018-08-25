package server.provider;

import com.google.gson.Gson;
import org.glassfish.jersey.internal.util.Base64;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	
	private static final String AUTHOTIZATION_HEADER_KEY = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
	private static final String SECURED_URL_PREFIX = "v1/animes/view/";
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
				
				if("user".equals(username) && "password".equals(password)){
					return;
				}
			}
			Gson g = new Gson();
			String str = g.toJson(RESPONSE_FAIL);
			
			Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED).entity(str).build();
			requestContext.abortWith(unauthorizedStatus);
		}
	}
}
