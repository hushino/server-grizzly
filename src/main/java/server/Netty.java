package server;

import io.netty.channel.Channel;
import org.glassfish.jersey.netty.httpserver.NettyHttpContainerProvider;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Netty {
	
	static final String ROOT_PATH = "";
	
	private static final URI BASE_URI = URI.create("http://localhost:8080/");
	
	public static void main(String[] args) {
		try{
			System.out.println("Jersey App on Netty container.");
			
			final ResourceConfig rc = new ResourceConfig().packages("server");
			final Channel server = NettyHttpContainerProvider.createHttp2Server(BASE_URI, rc, null);
			
			Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
				@Override
				public void run() {
					server.close();
				}
			}));
			
			System.out.println(String.format("Application started. (HTTP/2 enabled!)\nTry out %s%s\nStop the application using "
					+"CTRL+C.", BASE_URI, ROOT_PATH));
			Thread.currentThread().join();
		}catch(InterruptedException ex){
			Logger.getLogger(Netty.class.getName()).log(Level.ALL, null, ex);
		}
	}
}