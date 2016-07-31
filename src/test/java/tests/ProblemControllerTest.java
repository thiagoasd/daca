package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import io.restassured.http.ContentType;
import models.Problema;

public class ProblemControllerTest {

	@Test
	public void test() {

		// Right now we only return a Json with one Problema
		get("/Problema").then().body("id", hasItem(0)).assertThat().statusCode(200);
		get("/Problema").then().body("nome", hasItem("nome"));
		get("/Problema").then().body("codigo", hasItem("codigo"));
		get("/Problema").then().body("dica", hasItem("dica"));
		get("/Problema").then().body("descricao", hasItem("descricao"));
		get("/Problema").then().body("id", hasItem(0));

		Problema prob = new Problema("name", "cod", "dica", "desc");
		given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat().statusCode(200)
				.body("nome", is("name"));

		get("/Problema/0").then().body("id", equalTo(0)).assertThat().statusCode(is(200));

		prob.setNome("novoNome");
		given().contentType(ContentType.JSON).body(prob).when().put("/Problema/0").then().assertThat()
				.statusCode(is(200)).body("nome", is("novoNome"));

		prob.setCodigo("111");
		given().contentType(ContentType.JSON).body(prob).when().put("/Problema/0").then().assertThat()
				.statusCode(is(200)).body("codigo", is("111"));

		delete("/Problema/0").then().assertThat().statusCode(is(200));
		get("/Problema/1").then().assertThat().body("id", is(1)).statusCode(is(200));

	}

}
