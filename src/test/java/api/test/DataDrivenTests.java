package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DataDrivenTests {
	
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	public void testPostuser(String userId,String userName,String fname, String lname,String useremail,String pwd,String ph) {
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(useremail);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		
		Response response=UserEndPoints.create_user(userPayload);//UserEndPoints class called hear
		
		Assert.assertEquals(response.statusCode(),200 );
		
		
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteuserByName(String userName) {
		Response response=UserEndPoints.delete_user(userName);
		Assert.assertEquals(response.getStatusCode(), 200);
		
		}
	
	

}
