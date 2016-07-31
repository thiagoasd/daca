package bootwildfly;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.Solucao;

@RestController
public class SolucaoController {

	@RequestMapping(path = "Solucao", method = RequestMethod.GET)
	public Solucao[] Solucao(@RequestParam(required = false) boolean last) {

		Solucao[] sols = new Solucao[1];
		sols[0] = new Solucao(0, last, "body", (new String[] { "Out1", "Out2" }));
		return sols;
	}
	
	//Resource to find specific solutionS to the provided probID
	@RequestMapping(path = "Problema/{probID}/Solucao", method = RequestMethod.GET)
	public Solucao[] Solucao(@PathVariable int probID) {
		Solucao[] sols = new Solucao[1];
		sols[1] = new Solucao(0, true, "body", (new String[] { "Out1", "Out2" }));
		return sols;
	}

	@RequestMapping(path = "Solucao", method = RequestMethod.POST)
	public Solucao Solucao(@RequestBody Solucao sol) {

		return sol;
	}

	// ---------------- SOLUCAO/ID (C)RUD ----------------

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.GET)
	public Solucao SolucaoSpecific(@PathVariable int solID) {

		return new Solucao(solID, false, "body", (new String[] { "Out1", "Out2" }));
	}

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.PUT)
	public Solucao SolucaoPut(@PathVariable int solID, @RequestBody Solucao sol) {

		sol.setId(solID);
		return sol;
	}

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.DELETE)
	public Solucao SolucaoDelete(@PathVariable int solID) {

		return Solucao(false)[0];
	}

}