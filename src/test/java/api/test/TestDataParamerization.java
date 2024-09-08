package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoint;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class TestDataParamerization {
	
	
	
	@Test (priority=1, dataProvider= "getAllUserDataFromXL", dataProviderClass=DataProviders.class)
	public void testPostUser(String id, String user, String fname, String lname, String email, String password, String phone ) {
		
		
		User payload = new User();
		
		payload.setId(Integer.parseInt(id));
		payload.setUsername(user);
		payload.setFirstName(fname);
		payload.setLastName(lname);
		payload.setEmail(email);
		payload.setPhone(Integer.parseInt(phone));
		payload.setPassword(password);
		
        Response response= UserEndpoint.createUser(payload);
		
		response.then().log().all();
		
		Assert.assertEquals(response.statusCode(), 200);
		
		
	}

}
