package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import bootwildfly.ProblemaRepository;
import bootwildfly.SolucaoRepository;
import bootwildfly.TesteRepository;
import io.restassured.http.ContentType;
import models.Problema;
import models.Teste;

public class ProblemControllerTest {
	@Autowired
	ProblemaRepository PR;
	@Autowired
	SolucaoRepository SR;
	@Autowired
	TesteRepository TR;

	@Test
	public void ProblemaTest() {

		// Right now we only return a Json with one Problema
		get("/Problema").then().body("id", hasItem(1)).assertThat().statusCode(200);
		get("/Problema").then().body("nome", hasItem("nome 1"));
		get("/Problema").then().body("codigo", hasItem("codigo 1"));
		get("/Problema").then().body("dica", hasItem("dica 1"));
		get("/Problema").then().body("descricao", hasItem("descricao 1"));
		get("/Problema").then().body("id", hasItem(1));

		// Test Post and checks if the correct Json is been saved
		Problema prob = new Problema("name", "cod", "dica", "desc");
		given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat().statusCode(200);

		// Tests GET with Path Variable
		get("/Problema/1").then().body("id", equalTo(1)).assertThat().statusCode(is(200));

		// Tests PUT and checking if it really changed the JSON
		prob.setNome("novoNome");
		given().contentType(ContentType.JSON).body(prob).when().put("/Problema/1").then().assertThat()
				.statusCode(is(200));

		prob.setCodigo("111");
		given().contentType(ContentType.JSON).body(prob).when().put("/Problema/1").then().assertThat()
				.statusCode(is(200));

		// Tests DELETE
		delete("/Problema/1").then().assertThat().statusCode(is(200));

	}

	@Test
	public void TesteTest() {

		// Right now we only return a Json with one Teste
		get("/Problema/1/Teste").then().body("id", hasItem(1)).assertThat().statusCode(200);
		get("/Problema/1/Teste").then().body("dica", hasItem("dica 1"));
		get("/Problema/1/Teste").then().body("entrada", hasItem("entrada 1"));
		get("/Problema/1/Teste").then().body("nome", hasItem("nome 1"));
		get("/Problema/1/Teste").then().body("saida", hasItem("saida 1"));
		get("/Problema/1/Teste").then().body("id", hasItem(1));
		get("/Problema/1/Teste").then().body("problemID", hasItem(1));

		// Test Post and checks if the correct Json is been saved
		Teste teste = new Teste("nome", "dica", "entrada", "saida", 0, true);
		given().contentType(ContentType.JSON).body(teste).when().post("/Problema/0/Teste").then().assertThat()
				.statusCode(200).body("nome", is("nome"));

		// Tests GET with Path Variable
		get("/Problema/1/Teste/1").then().body("id", equalTo(1)).assertThat().statusCode(is(200));

		// Tests PUT and checking if it really changed the JSON
		teste.setNome("novoNome");
		given().contentType(ContentType.JSON).body(teste).when().put("/Problema/1").then().assertThat()
				.statusCode(is(200)).body("nome", is("novoNome"));

		teste.setDica("XXX");
		given().contentType(ContentType.JSON).body(teste).when().put("/Problema/1").then().assertThat()
				.statusCode(is(200)).body("dica", is("XXX"));

		// Tests DELETE
		delete("/Problema/1/Teste/1").then().assertThat().statusCode(is(200));
	}

	@Before
	public void Init() {

		for (int i = 1; i < 10; i++) {
			Problema prob = new Problema("nome " + i, "codigo " + i, "dica " + i, "descricao " + i);
			prob.setId(i);
			given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
					.statusCode(200);

			Teste test = new Teste("nome ", "dica " + i, "entrada " + i, "saida " + i, i, true);
			given().contentType(ContentType.JSON).body(test).when().post("/Problema/" + i + "/Teste").then()
					.assertThat().statusCode(200);
			
			test = new Teste("nome ", "dica " + i, "entrada " + i, "saida " + i, i, false);
			given().contentType(ContentType.JSON).body(test).when().post("/Problema/" + i + "/Teste").then()
			.assertThat().statusCode(200);
		}

	}

}
