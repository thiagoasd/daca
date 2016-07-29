package bootwildfly;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Problema;

@RestController
public class ProblemaConstroller {

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

		return new Problema();
	}

}
