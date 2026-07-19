package api;

import static io.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class UserApiTest {

	@Test
	public void createUserTest() {

	    RestAssured.baseURI =
	            "https://jsonplaceholder.typicode.com";


	    String requestBody =
	            "{\n" +
	            "  \"name\": \"Ritika\",\n" +
	            "  \"email\": \"ritika@test.com\",\n" +
	            "  \"job\": \"QA Engineer\"\n" +
	            "}";


	    Response response =
	            given()
	            .header("Content-Type", "application/json")
	            .body(requestBody)
	            .when()
	            .post("/users");


	    response.prettyPrint();


	    Assert.assertEquals(response.getStatusCode(),201);


	    String name =
	            response.jsonPath()
	            .getString("name");


	    Assert.assertEquals(name,"Ritika");

	}

}