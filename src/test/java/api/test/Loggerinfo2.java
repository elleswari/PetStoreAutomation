package api.test;


import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;


import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class Loggerinfo2 {
	


	
		Faker faker;
		User userPayload;
		public Logger logger;
		
		@BeforeClass
		public void setup() {
			faker = new Faker();
			userPayload = new User();
			
			userPayload.setId(faker.idNumber().hashCode());
			userPayload.setUsername(faker.name().username());
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			userPayload.setPassword(faker.internet().password(5, 10));
			userPayload.setPhone(faker.phoneNumber().cellPhone());
			
			//logs
			logger =LogManager.getLogger(this.getClass());
			logger.debug("debugging");
			}
		@Test(priority =1)
		 public void testPost() 
		{
			logger.info("********* Creating user ********");
			Response response=UserEndPoints2.create_user(userPayload);//UserEndPoints class called hear
			response.then().log().all();
			Assert.assertEquals(response.statusCode(),200 );
			logger.info("*********  user is created ********");
			
			
		}
		@Test(priority=2)
		public void testGetUserByName() {
			logger.info("********* reading user info ********");
			Response response=UserEndPoints2.read_user(this.userPayload.getUsername());
			response.then().log().all();
			
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("********* user info is displayed ********");
		}
		
		//@Test(priority=3)
		public void testUpdateUserByName() {
			//update data using payload
			logger.info("********* upadating user ********");
			userPayload.setFirstName(faker.name().firstName());
			userPayload.setLastName(faker.name().lastName());
			userPayload.setEmail(faker.internet().safeEmailAddress());
			 
			Response response = UserEndPoints2.update_user(this.userPayload.getUsername(),userPayload);
			response.then().log().body();
			//response.then().body().statusCode(200);
			Assert.assertEquals(response.getStatusCode(),200);
			logger.info("********* user is upadated ********");
			//checking data after update
			Response responseAfterUpdate=UserEndPoints2.read_user(this.userPayload.getUsername());
			Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
			}
		
		@Test(priority=4)
		
		public void testDeleteUserByName() {
			logger.info("********* Deleting user ********");
			Response response=UserEndPoints2.delete_user(this.userPayload.getUsername());
			Assert.assertEquals(response.getStatusCode(), 200);
			logger.info("*********  user is deleted ********");
		}

	}



