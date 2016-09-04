package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.Test;

import io.restassured.http.ContentType;
import models.Solucao;;

public class SolucaoControllerTest {

	@Test
	public void SolucaoTest() {
		
		// Test GET
		get("/Solucao").then().body("id", hasItem(0)).assertThat().statusCode(200);
		get("/Solucao").then().body("last", hasItem(false));
		get("/Solucao").then().body("body", hasItem("body"));
		
		//TODO LEARN HOW TO CHECK STRING ARRAYS IN BODY RESPONSE
		//get("/Solucao").then().body("outputs", is((new String[] { "Out1", "Out2" })));

		//Test POST
		Solucao sol = new Solucao(0, true, "corpo", Arrays.asList("oi", "oi"), 0);
		given().contentType(ContentType.JSON).body(sol).when().post("/Solucao").then().assertThat().statusCode(200)
				.body("body", is("corpo"));

		// Tests GET with Path Variable
		get("/Solucao/0").then().assertThat().body("id", is(0)).statusCode(is(200));
		get("/Solucao/1").then().assertThat().body("id", is(1)).statusCode(is(200));

		// Tests PUT and checking if it really changed the JSON
		sol.setBody("novoBody");
		given().contentType(ContentType.JSON).body(sol).when().put("/Solucao/0").then().assertThat().statusCode(is(200))
				.body("body", is("novoBody"));

		sol.setLast(true);
		given().contentType(ContentType.JSON).body(sol).when().put("/Solucao/0").then().assertThat().statusCode(is(200))
				.body("last", is(true));

		// Tests DELETE
		delete("/Solucao/0").then().assertThat().statusCode(is(200));
		
		

	}

}
