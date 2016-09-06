package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bootwildfly.ProblemaRepository;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import models.Problema;
import models.Teste;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = bootwildfly.Application.class)
public class ProblemControllerTest {

	@Autowired
	ProblemaRepository PR;

	@Test
	public void ProblemaGet() {
		Random ran = new Random();
		int ID = ran.nextInt(11) + 1;

		get("/Problema").then().assertThat().statusCode(200);
		get("/Problema").then().body("nome", hasItem("nome " + ID));
		get("/Problema").then().body("codigo", hasItem("codigo " + ID));
		get("/Problema").then().body("dica", hasItem("dica " + ID));
		get("/Problema").then().body("descricao", hasItem("descricao " + ID));
		get("/Problema").then().body("id", hasItem(ID));

	}

	@Test
	public void ProblemaPost() {
		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");

		int ID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		get("/Problema/{id}", ID).then().assertThat().statusCode(200);
		get("/Problema/{id}", ID).then().body("nome", is("nome1"));
		get("/Problema/{id}", ID).then().body("codigo", is("cod2"));
		get("/Problema/{id}", ID).then().body("dica", is("dica3"));
		get("/Problema/{id}", ID).then().body("descricao", is("descricao4"));
		get("/Problema/{id}", ID).then().body("id", is(ID));

	}

	@Test
	public void ProblemaDelete() {

		Problema prob = new Problema("nome", "codigo", "dica", "descricao");
		int ID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(200).extract().path("objectId");
		get("/Problema/{id}", ID).then().assertThat().statusCode(200);
		get("/Problema/{id}", ID).then().body("codigo", is("codigo"));
		get("/Problema/{id}", ID).then().body("descricao", is("descricao"));
		get("/Problema/{id}", ID).then().body("dica", is("dica"));
		get("/Problema/{id}", ID).then().body("nome", is("nome"));
		get("/Problema/{id}", ID).then().body("id", is(ID));

		delete("/Problema/{id}", ID).then().assertThat().statusCode(200);
		get("/Problema/{id}", ID).then().assertThat().statusCode(404);

	}

	@Test
	public void ProblemaPut() {
		int ID;

		// Test Post and checks if the correct Json is been saved
		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");

		ID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		get("/Problema/{id}", ID).then().assertThat().statusCode(200);
		get("/Problema/{id}", ID).then().body("nome", is("nome1"));
		get("/Problema/{id}", ID).then().body("codigo", is("cod2"));
		get("/Problema/{id}", ID).then().body("dica", is("dica3"));
		get("/Problema/{id}", ID).then().body("descricao", is("descricao4"));
		get("/Problema/{id}", ID).then().body("id", is(ID));

		prob.setNome("nome5");
		prob.setCodigo("cod6");
		prob.setDica("dica7");
		prob.setDescricao("descricao8");

		given().contentType(ContentType.JSON).body(prob).when().put("/Problema/{ID}", ID).then().assertThat()
				.statusCode(is(200));

		get("/Problema/{id}", ID).then().assertThat().statusCode(200);
		get("/Problema/{id}", ID).then().body("nome", is("nome5"));
		get("/Problema/{id}", ID).then().body("codigo", is("cod6"));
		get("/Problema/{id}", ID).then().body("dica", is("dica7"));
		get("/Problema/{id}", ID).then().body("descricao", is("descricao8"));
		get("/Problema/{id}", ID).then().body("id", is(ID));

	}

	@Test
	public void TesteGet() {
		int ID;
		int ProblemID = 1;

		ID = get("/Problema/{id}/Teste", ProblemID).then().assertThat().statusCode(200).extract().path("get(0).id");
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("id", is(ID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("nome", is("nome 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("dica", is("dica 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("entrada", is("entrada 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("saida", is("saida 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("problemID", is(ProblemID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("publico", is(true));

		ID = get("/Problema/{id}/Teste", ProblemID).then().assertThat().statusCode(200).extract().path("get(1).id");
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("id", is(ID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("nome", is("nome 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("dica", is("dica 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("entrada", is("entrada 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("saida", is("saida 1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("problemID", is(ProblemID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("publico", is(false));

	}

	@Test
	public void TestePost() {

		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");
		int ProblemID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		Teste test = new Teste("nome1", "dica2", "entrada3", "saida4", ProblemID, true);
		int ID = given().contentType(ContentType.JSON).body(test).when().post("/Problema/" + ProblemID + "/Teste")
				.then().assertThat().statusCode(200).extract().path("objectId");

		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("id", is(ID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("nome", is("nome1"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("dica", is("dica2"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("entrada", is("entrada3"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("saida", is("saida4"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("problemID", is(ProblemID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("publico", is(true));

	}

	@Test
	public void TestePut() {

		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");
		int ProblemID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		Teste test = new Teste("nome5", "dica6", "entrada7", "saida8", ProblemID, true);
		int ID = given().contentType(ContentType.JSON).body(test).when().post("/Problema/" + ProblemID + "/Teste")
				.then().assertThat().statusCode(200).extract().path("objectId");

		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("id", is(ID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("nome", is("nome5"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("dica", is("dica6"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("entrada", is("entrada7"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("saida", is("saida8"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("problemID", is(ProblemID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("publico", is(true));

		test = new Teste("nome9", "dica10", "entrada11", "saida12", ProblemID, false);
		given().contentType(ContentType.JSON).body(test).when().put("/Problema/" + ProblemID + "/Teste/" + ID).then()
				.assertThat().statusCode(200);

		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("id", is(ID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("nome", is("nome9"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("dica", is("dica10"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("entrada", is("entrada11"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("saida", is("saida12"));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("problemID", is(ProblemID));
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().body("publico", is(false));
	}

	@Test
	public void TesteDelete() {
		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");
		int ProblemID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		Teste test = new Teste("nome5", "dica6", "entrada7", "saida8", ProblemID, true);
		int ID = given().contentType(ContentType.JSON).body(test).when().post("/Problema/" + ProblemID + "/Teste")
				.then().assertThat().statusCode(200).extract().path("objectId");

		delete("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().statusCode(200);
		get("/Problema/{ProblemID}/Teste/{id}", ProblemID, ID).then().assertThat().statusCode(404);

	}

	@Before
	public void init() {
		
		RestAssured.authentication = form("daca", "daca", new FormAuthConfig("/login","username", "password"));

		if (PR.count() == 0) {

			for (int i = 1; i < 12; i++) {
				Problema prob = new Problema("nome " + i, "codigo " + i, "dica " + i, "descricao " + i);
				prob.setId(i);
				given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
						.statusCode(200);

				Teste test = new Teste("nome " + i, "dica " + i, "entrada " + i, "saida " + i, i, true);
				given().contentType(ContentType.JSON).body(test).when().post("/Problema/" + i + "/Teste").then()
						.assertThat().statusCode(200);

				test = new Teste("nome " + i, "dica " + i, "entrada " + i, "saida " + i, i, false);
				given().contentType(ContentType.JSON).body(test).when().post("/Problema/" + i + "/Teste").then()
						.assertThat().statusCode(200);
			}
		}

	}

}
