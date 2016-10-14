package tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bootwildfly.ProblemaRepository;
import bootwildfly.SolucaoRepository;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import models.Problema;
import models.Solucao;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = bootwildfly.Application.class)
public class SolucaoControllerTest {

	@Autowired
	ProblemaRepository PR;

	@Autowired
	SolucaoRepository SR;

	@Test
	public void SolucaoGet() {
		Random ran = new Random();
		int ID = ran.nextInt(11) + 1;

		get("/Solucao").then().assertThat().statusCode(200).extract();
		get("/Solucao").then().body("id", hasItem(ID));
		get("/Solucao").then().body("last", hasItem(true));
		get("/Solucao").then().body("body", hasItem("corpo"));
		get("/Solucao").then().body("problemID", hasItem(ID));
	}

	@Test
	public void SolucaoPost() {

		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");
		int ProblemID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		Solucao sol = new Solucao(false, "1", Arrays.asList("out1", "out2"), ProblemID);
		int ID = given().contentType(ContentType.JSON).body(sol).when().post("/Solucao").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		get("/Solucao/{ID}", ID).then().assertThat().statusCode(200).extract();
		get("/Solucao/{ID}", ID).then().body("id", is(ID));
		get("/Solucao/{ID}", ID).then().body("last", is(false));
		get("/Solucao/{ID}", ID).then().body("body", is("1"));
		get("/Solucao/{ID}", ID).then().body("problemID", is(ProblemID));
	}

	@Test
	public void SolucaoPut() {
		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");
		int ProblemID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		Solucao sol = new Solucao(false, "1", Arrays.asList("out1", "out2"), ProblemID);
		int ID = given().contentType(ContentType.JSON).body(sol).when().post("/Solucao").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		get("/Solucao/{ID}", ID).then().assertThat().statusCode(200).extract();
		get("/Solucao/{ID}", ID).then().body("id", is(ID));
		get("/Solucao/{ID}", ID).then().body("last", is(false));
		get("/Solucao/{ID}", ID).then().body("body", is("1"));
		get("/Solucao/{ID}", ID).then().body("problemID", is(ProblemID));

		sol = new Solucao(true, "2", Arrays.asList("out3", "out4"), ProblemID);
		given().contentType(ContentType.JSON).body(sol).when().put("/Solucao/" + ID).then().assertThat()
				.statusCode(200);

		get("/Solucao/{ID}", ID).then().assertThat().statusCode(200).extract();
		get("/Solucao/{ID}", ID).then().body("id", is(ID));
		get("/Solucao/{ID}", ID).then().body("last", is(true));
		get("/Solucao/{ID}", ID).then().body("body", is("2"));
		get("/Solucao/{ID}", ID).then().body("problemID", is(ProblemID));

	}

	@Test
	public void SolucaoDelete() {
		Problema prob = new Problema("nome1", "cod2", "dica3", "descricao4");
		int ProblemID = given().contentType(ContentType.JSON).body(prob).when().post("/Problema").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		Solucao sol = new Solucao(false, "1", Arrays.asList("out1", "out2"), ProblemID);
		int ID = given().contentType(ContentType.JSON).body(sol).when().post("/Solucao").then().assertThat()
				.statusCode(is(200)).extract().path("objectId");

		delete("/Solucao/{id}", ID).then().assertThat().statusCode(200);
		get("/Solucao/{id}", ID).then().assertThat().statusCode(404);
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

				Solucao sol = new Solucao(true, "corpo", Arrays.asList("out1", "out2"), i);
				given().contentType(ContentType.JSON).body(sol).when().post("/Solucao").then().assertThat()
						.statusCode(200);

			}
		}

	}
}
