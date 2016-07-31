package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import io.restassured.http.ContentType;
import models.Problema;
import models.Teste;

public class ProblemControllerTest {

	@Test
	public void ProblemaTest() {

		// Right now we only return a Json with one Problema
		get("/Problema").then().body("id", hasItem(0)).assertThat().statusCode(200);
		get("/Problema").then().body("nome", hasItem("nome"));
		get("/Problema").then().body("codigo", hasItem("codigo"));
		get("/Problema").then().body("dica", hasItem("dica"));
		get("/Problema").then().body("descricao", hasItem("descricao"));
		get("/Problema").then().body("id", hasItem(0));

		// Test Post and checks if the correct Json is been saved
		Problema prob = new Problema("name", "cod", "dica", "desc");
		given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat().statusCode(200)
				.body("nome", is("name"));

		// Tests GET with Path Variable
		get("/Problema/0").then().body("id", equalTo(0)).assertThat().statusCode(is(200));

		// Tests PUT and checking if it really changed the JSON
		prob.setNome("novoNome");
		given().contentType(ContentType.JSON).body(prob).when().put("/Problema/0").then().assertThat()
				.statusCode(is(200)).body("nome", is("novoNome"));

		prob.setCodigo("111");
		given().contentType(ContentType.JSON).body(prob).when().put("/Problema/0").then().assertThat()
				.statusCode(is(200)).body("codigo", is("111"));

		// Tests DELETE
		delete("/Problema/0").then().assertThat().statusCode(is(200));

	}

	@Test
	public void TesteTest() {

		// Right now we only return a Json with one Teste
		get("/Problema/0/Teste").then().body("id", hasItem(0)).assertThat().statusCode(200);
		get("/Problema/0/Teste").then().body("dica", hasItem("dica"));
		get("/Problema/0/Teste").then().body("entrada", hasItem("entrada"));
		get("/Problema/0/Teste").then().body("nome", hasItem("nome"));
		get("/Problema/0/Teste").then().body("saida", hasItem("saida"));
		get("/Problema/0/Teste").then().body("id", hasItem(0));
		get("/Problema/0/Teste").then().body("problemID", hasItem(0));

		// Test Post and checks if the correct Json is been saved
		Teste teste = new Teste("dica", "entrada", "nome", "saida", 0, 0, true);
		given().contentType(ContentType.JSON).body(teste).when().post("/Problema/0/Teste").then().assertThat().statusCode(200)
				.body("nome", is("nome"));

		// Tests GET with Path Variable
		get("/Problema/0/Teste/1").then().body("id", equalTo(1)).assertThat().statusCode(is(200));

		// Tests PUT and checking if it really changed the JSON
		teste.setNome("novoNome");
		given().contentType(ContentType.JSON).body(teste).when().put("/Problema/0").then().assertThat()
				.statusCode(is(200)).body("nome", is("novoNome"));

		teste.setDica("XXX");
		given().contentType(ContentType.JSON).body(teste).when().put("/Problema/0").then().assertThat()
				.statusCode(is(200)).body("dica", is("XXX"));

		// Tests DELETE
				delete("/Problema/0/Teste/0").then().assertThat().statusCode(is(200));
	}

}
