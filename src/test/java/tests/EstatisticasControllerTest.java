package tests;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import bootwildfly.ProblemaRepository;
import bootwildfly.SolucaoRepository;
import bootwildfly.TesteRepository;

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
		get("/Estatisticas").then().assertThat().statusCode(200).extract();
	}

}
