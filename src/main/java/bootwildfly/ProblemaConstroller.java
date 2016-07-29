package bootwildfly;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Problema;
import models.Teste;

@RestController
public class ProblemaConstroller {

	//---------------- PROBLEMA CRUD ----------------
	@RequestMapping(path = "Problema", method = RequestMethod.GET)
	public Problema Problema() {

		return new Problema(0, "name", "codigo", "dica", "descricao");
	}

	@RequestMapping(path = "Problema", method = RequestMethod.POST)
	public Problema Problema(@RequestBody Problema prob) {

		return prob;
	}

	// ---------------- PROBLEMA/ID (C)RUD ----------------
	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.GET)
	public Problema ProblemaSpecific(@PathVariable int probID) {

		return new Problema(probID, "name", "codigo", "dica", "descricao");
	}

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.PUT)
	public Problema ProblemaPut(@PathVariable int probID, @RequestBody Problema prob) {

		prob.setId(probID);
		return prob;
	}

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.DELETE)
	public Problema ProblemaDelete(@PathVariable int probID) {

		return Problema();
	}
	
	// ---------------- TESTE CRUD ----------------

	@RequestMapping(path = "Problema/{probID}/Teste", method = RequestMethod.GET)
	public Teste Teste(@PathVariable int probID) {

		return new Teste("dica", "entrada", "nome", "saida", 0, true);
	}
	
	@RequestMapping(path = "Problema/{probID}/Teste", method = RequestMethod.POST)
	public Teste Teste(@RequestBody Teste teste) {

		return teste;
	}
	
	// ---------------- TESTE/ID (C)RUD ----------------
	
	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.GET)
	public Teste TesteSpecific(@PathVariable int probID, @PathVariable int testeID) {

		return new Teste("dica", "entrada", "nome", "saida", testeID, probID, false);
	}

	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.PUT)
	public Teste TestePut(@PathVariable int probID, @PathVariable int testeID, @RequestBody Teste teste) {

		teste.setId(probID);
		return teste;
	}

	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.DELETE)
	public Teste TesteDelete(@PathVariable int probID) {

		return Teste(probID);
	}

	

}
