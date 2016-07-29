package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;


import io.restassured.http.ContentType;
import models.Problema;

public class ProblemControllerTest {

	@Test
	public void test() {
		Map<String, Object>  jsonAsMap = new HashMap<>();
		jsonAsMap.put("name", "nome");
		jsonAsMap.put("codigo", "nome");
		jsonAsMap.put("dica", "nome");
		jsonAsMap.put("descricao", "nome");
		jsonAsMap.put("id", 0);


		get("/Problema").then().body("id", equalTo(0));
		
		given().contentType(ContentType.JSON).body(jsonAsMap).
		when().post("/Problema").then().assertThat().statusCode(200);
		
		Problema prob = new Problema("name", "cod", "dica", "desc");
		given().contentType(ContentType.JSON).body(prob).
		when().post("/Problema").then().assertThat().statusCode(200);

	}

}
