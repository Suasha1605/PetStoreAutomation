package api.endpoints;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import api.payload.User;
import io.restassured.response.Response;

public class UserEndpoint {
	
	@Test
	public static Response createUser(User payload) {
		
		Response response= given()
		.contentType("application/json")
		.accept("application/json")
		.body(payload)
		
		.when()
		.post(Route.post_url);
		
		return response;
		
		
		
	}
	
	
	@Test
	public static Response getUser(String username) {
		
		Response response= given()
		.pathParam("username", username)
		
		.when()
		.get(Route.get_url);
		
		return response;
		
		
		
	}
	
	
	@Test
	public static Response updateUser(User payload, String username) {
		
		Response response= given()
		.contentType("application/json")
		.accept("application/json")
		.pathParam("username", username)
		.body(payload)
		
		
		.when()
		.put(Route.put_url);
		
		return response;
		
		
		
	}
	
	@Test
	public static Response deleteUser(String username) {
		
		Response response= given()
		           .pathParam("username", username)
		
		
		
		.when()
		.delete(Route.delete_url);
		
		return response;
		
		
		
	}

}
