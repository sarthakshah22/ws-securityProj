package testClient;

import java.net.URI;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;

import Decoder.BASE64Encoder;

public class JersyGetClient {
	 public static void main(String a[]){
         
	        String url = "http://localhost:8080/RestfulWebServices/order-inventory/order/1016";
/*	        String name = "sarthak";
	        String password = "Inf@1238$235";
	        String authString = name + ":" + password;*/
	        String authString = "InfoMobileAuthCode:000";
	        String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
	        System.out.println("Base64 encoded auth string: " + authStringEnc);
	   /*     ClientConfig config = new ClientConfig();
            Client client = ClientBuilder.newClient(config);
            WebTarget target = client.target(getBaseURI());
            String resp = target.path("users").request().accept(MediaType.APPLICATION_XML)
            		.header("Authorization", "Basic " + authStringEnc).get(Response.class)
                    .toString();
	        System.out.println("response: "+resp);*/
	    }
	 
	 private static URI getBaseURI() {
         return UriBuilder.fromUri("http://localhost:8080/RESTful_services/rest/UserService").build();
 }
}
