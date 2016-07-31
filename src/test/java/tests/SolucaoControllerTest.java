package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;


import io.restassured.http.ContentType;
import models.Solucao;;

public class SolucaoControllerTest {

	@Test
	public void test() {
		// Right now we only return a Json with one Problema
				get("/Solucao").then().body("id", hasItem(0)).assertThat().statusCode(200);
				get("/Solucao").then().body("last", hasItem(true));
				get("/Solucao").then().body("body", hasItem("body"));
				get("/Solucao").then().body("saidas", hasItem((new String[] { "Out1", "Out2" })));


				Solucao sol = new Solucao(0, true, "body", (new String[] { "Out1", "Out2" }));
				given().contentType(ContentType.JSON).body(sol).when().post("/Solucao").then().assertThat().statusCode(200)
						.body("body", is("body"));

				get("/Solucao/0").then().body("id", equalTo(0)).assertThat().statusCode(is(200));

				sol.setBody("novoBody");
				given().contentType(ContentType.JSON).body(sol).when().put("/Solucao/0").then().assertThat()
						.statusCode(is(200)).body("body", is("novoBody"));

				sol.setLast(false);
				given().contentType(ContentType.JSON).body(sol).when().put("/Solucao/0").then().assertThat()
						.statusCode(is(200)).body("last", is(true));

				delete("/Solucao/0").then().assertThat().statusCode(is(200));
				get("/Solucao/1").then().assertThat().body("id", is(1)).statusCode(is(200));

	}

}
