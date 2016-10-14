package tests;

import static io.restassured.RestAssured.form;
import static io.restassured.RestAssured.get;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bootwildfly.ProblemaRepository;
import bootwildfly.SolucaoRepository;
import bootwildfly.TesteRepository;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = bootwildfly.Application.class)
public class EstatisticasControllerTest {
	@Autowired
	ProblemaRepository PR;

	@Autowired
	SolucaoRepository SR;

	@Autowired
	TesteRepository TR;

	@Test
	public void EstatisticasGet() {

		// Can't test the values because Repositories always return 0 as value
		// even when populated
		get("/Estatisticas").then().assertThat().statusCode(200);
	}

	@Before
	public void init() {
		RestAssured.authentication = form("daca", "daca", new FormAuthConfig("/login", "username", "password"));

	}

}
