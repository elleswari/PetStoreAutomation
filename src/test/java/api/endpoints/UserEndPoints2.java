package api.endpoints;

import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.response.Response;

//userendpoints.java
//creater for CURD operations.create,read,update,delete.

public class UserEndPoints2 {
	//method created for getting url's from properties file
	static ResourceBundle getURL(){
		ResourceBundle routes=ResourceBundle.getBundle("routes");//load from properties file routesProperties
		return routes;
	}
	
	public static Response create_user(User payload) 
	{
		String post_url=getURL().getString("post_url");
		
		Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .body(payload)
		.when()
		     .post(post_url);
		return response;
		}
	
	public static Response read_user(String userName) 
	{
		String get_url=getURL().getString("get_url");
		
		Response response=given()
		   .pathParam("username", userName)
		.when()
		     .get(get_url);
		return response;
		}
	
	public static Response update_user(String userName,User payload) 
	{
		String update_url=getURL().getString("update_url");
		Response response=given()
		    .contentType(ContentType.JSON)
		    .accept(ContentType.JSON)
		    .pathParam("userName", userName)
		    .body(payload)
		.when()
		     .put(update_url);
		return response;
		}
	
	public static Response delete_user(String userName) 
	{
		String delete_url=getURL().getString("delete_url");
		Response response=given()
		   .pathParam("username", userName)
		.when()
		     .delete(delete_url);
		return response;
		}

	
	}


