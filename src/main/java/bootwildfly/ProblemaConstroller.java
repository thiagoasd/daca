package bootwildfly;

import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import models.Problema;
import models.Teste;

@RestController
public class ProblemaConstroller {
	@PersistenceContext
	EntityManager em;

	@Autowired
	ProblemaRepository PR;

	// ---------------- PROBLEMA CRUD ----------------
	@RequestMapping(path = "Problema", method = RequestMethod.GET)
	public List<Problema> Problema() {

		List<Problema> probs = PR.findAll();
		return probs;
	}

	@RequestMapping(path = "Problema", method = RequestMethod.POST)
	public ResponseEntity<?> Problema(@Valid @RequestBody Problema prob, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("The object contain errors");
		}

		PR.save(prob);
		return ResponseEntity.ok("Problem saved");
	}

	// ---------------- PROBLEMA/ID (C)RUD ----------------

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.GET)
	public ResponseEntity<?> ProblemaSpecific(@PathVariable long probID) {

		if (!PR.exists(probID)) {
			return new ResponseEntity<>("No Problem found with id " + probID, HttpStatus.NOT_FOUND);
		}

		Problema prob = PR.findById(probID);
		return new ResponseEntity<>(prob, HttpStatus.OK);
	}

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.PUT)
	public ResponseEntity<?> ProblemaPut(@PathVariable long probID, @Valid @RequestBody Problema prob,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("The object contain errors", HttpStatus.BAD_REQUEST);
		} else if (!PR.exists(probID)) {
			return new ResponseEntity<>("Problem with ID " + probID + " not found", HttpStatus.NOT_FOUND);
		} else {
			prob.setId(probID);
			PR.save(prob);
			return new ResponseEntity<>("Problem edited", HttpStatus.OK);
		}
	}

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> ProblemaDelete(@PathVariable long probID) {
		if (!PR.exists(probID)) {
			return new ResponseEntity<>("Problem with ID " + probID + " not found", HttpStatus.NOT_FOUND);
		}
		
		PR.delete(probID);
		return ResponseEntity.ok("Problem " + probID + " deleted");
	}

	// ---------------- TESTE CRUD ----------------
	@RequestMapping(path = "Problema/{probID}/Teste", method = RequestMethod.GET)
	public Teste[] Teste(@PathVariable int probID) {

		Teste[] testes = new Teste[1];
		testes[0] = new Teste("dica", "entrada", "nome", "saida", 0, 0, true);
		return testes;
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

		return Teste(probID)[0];
	}

}
