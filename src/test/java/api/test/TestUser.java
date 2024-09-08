package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoint;
import api.payload.User;
import io.restassured.response.Response;

public class TestUser {
	
	
	Faker fake;
	
	User payload;
	
	
	@BeforeClass
	public void setData() {
		
		fake = new Faker();
		payload = new User();
		
		payload.setId(fake.idNumber().hashCode());
		payload.setFirstName(fake.name().firstName());
		payload.setLastName(fake.name().lastName());
		payload.setUsername(fake.name().username());
		payload.setEmail(fake.internet().safeEmailAddress());
		payload.setPassword(fake.internet().password(5,10));
		payload.setPhone(fake.phoneNumber().hashCode());
	}
	
	@Test (priority=1)
	public void testCreateUser() {
		
		Response response= UserEndpoint.createUser(payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	
	@Test (priority=2)
	public void testGetUser() {
		
		Response response= UserEndpoint.getUser(this.payload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test (priority=3)
	public void testUpdateUser() {
		
		payload.setFirstName(fake.name().firstName());
		payload.setLastName(fake.name().lastName());
		payload.setEmail(fake.internet().safeEmailAddress());
		
		Response response= UserEndpoint.updateUser(payload, this.payload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
//		checking updated data
		
		Response updatedResponse =UserEndpoint.getUser(this.payload.getUsername());
		
		updatedResponse.then().log().all();
		
	}
	
	
	@Test (priority=4)
	public void testdeleteUser() {
		
		Response response= UserEndpoint.deleteUser(this.payload.getUsername());
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
//		checking data is deleted or not
		
		Response ResponseAfterDelete =UserEndpoint.getUser(this.payload.getUsername());
		
		ResponseAfterDelete.then().log().all();
		
		Assert.assertEquals(ResponseAfterDelete.getStatusCode(), 404);
		
	}
	
	

}
