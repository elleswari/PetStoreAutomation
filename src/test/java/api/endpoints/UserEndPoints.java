package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import api.payload.User;
import io.restassured.response.Response;

//userendpoints.java
//creater for CURD operations.create,read,update,delete.

public class UserEndPoints {
	
	public static Response create_user(User payload) 
	{
		
		Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
		     .post(Routes.post_url);
		return response;
		}
	
	public static Response read_user(String userName) 
	{
		
		Response response=given()
		   .pathParam("username", userName)
		.when()
		     .get(Routes.get_url);
		return response;
		}
	
	public static Response update_user(String userName,User payload) 
	{
		
		Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("userName", userName)
		    .body(payload)
		.when()
		     .put(Routes.update_url);
		return response;
		}
	
	public static Response delete_user(String userName) 
	{
		
		Response response=given()
		   .pathParam("username", userName)
		.when()
		     .delete(Routes.delete_url);
		return response;
		}

	
	}


