package bootwildfly;

import org.springframework.web.bind.annotation.RequestMethod;
import java.util.List;
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

	@Autowired
	ProblemaRepository PR;

	@Autowired
	TesteRepository TR;

	// ---------------- PROBLEMA CRUD ----------------
	@RequestMapping(path = "Problema", method = RequestMethod.GET)
	public ResponseEntity<?> Problema() {

		List<Problema> probs = PR.findAll();
		return new ResponseEntity<>(probs, HttpStatus.OK);
	}

	@RequestMapping(path = "Problema", method = RequestMethod.POST)
	public ResponseEntity<?> Problema(@Valid @RequestBody Problema prob, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("The object contain errors");
		}

		PR.save(prob);
		return ResponseEntity.ok("Problema saved");
	}

	// ---------------- PROBLEMA/ID (C)RUD ----------------

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.GET)
	public ResponseEntity<?> ProblemaSpecific(@PathVariable long probID) {

		if (!PR.exists(probID)) {
			return new ResponseEntity<>("No Problema found with id " + probID, HttpStatus.NOT_FOUND);
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
			return new ResponseEntity<>("Problema with ID " + probID + " not found", HttpStatus.NOT_FOUND);
		} else {
			prob.setId(probID);
			PR.save(prob);
			return new ResponseEntity<>("Problema edited", HttpStatus.OK);
		}
	}

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> ProblemaDelete(@PathVariable long probID) {
		if (!PR.exists(probID)) {
			return new ResponseEntity<>("Problema with ID " + probID + " not found", HttpStatus.NOT_FOUND);
		}

		PR.delete(probID);
		return ResponseEntity.ok("Problema " + probID + " deleted");
	}

	// ---------------- TESTE CRUD ----------------
	@RequestMapping(path = "Problema/{probID}/Teste", method = RequestMethod.GET)
	public ResponseEntity<?> Teste(@PathVariable long probID) {

		ResponseEntity<?> response;
		if (!PR.exists(probID)) {
			response = new ResponseEntity<>("No Problema found with id " + probID, HttpStatus.NOT_FOUND);
		} else {

			List<Teste> tests = TR.findByProblemID(probID);
			if (tests.size() == 0) {
				response = new ResponseEntity<>("No Teste found for this Problema", HttpStatus.NO_CONTENT);
			} else {
				response = new ResponseEntity<>(tests, HttpStatus.OK);
			}
		}

		return response;
	}

	@RequestMapping(path = "Problema/{probID}/Teste", method = RequestMethod.POST)
	public ResponseEntity<?> Teste(@PathVariable long probID, @Valid @RequestBody Teste teste,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("The object contain errors");
		} else if (!PR.exists(probID)) {
			return new ResponseEntity<>("Problema with ID " + probID + " not found", HttpStatus.NOT_FOUND);
		} else {
			TR.save(teste);
			return ResponseEntity.ok("Teste saved");
		}
	}

	// ---------------- TESTE/ID (C)RUD ----------------

	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.GET)
	public ResponseEntity<?> TesteSpecific(@PathVariable long probID, @PathVariable long testeID) {

		if (!PR.exists(probID)) {
			return new ResponseEntity<>("No Problema found with id " + probID, HttpStatus.NOT_FOUND);
		} else if (!TR.exists(testeID)) {
			return new ResponseEntity<>("No Teste found with id " + testeID, HttpStatus.NOT_FOUND);
		}

		Teste teste = TR.findById(testeID);
		return new ResponseEntity<>(teste, HttpStatus.OK);

	}

	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.PUT)
	public ResponseEntity<?> TestePut(@PathVariable long probID, @Valid @PathVariable long testeID,
			@RequestBody Teste teste, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("The object contain errors", HttpStatus.BAD_REQUEST);
		} else if (!PR.exists(probID)) {
			return new ResponseEntity<>("Problema with ID " + probID + " not found", HttpStatus.NOT_FOUND);
		} else if (!TR.exists(testeID)) {
			return new ResponseEntity<>("Teste with ID " + testeID + " not found", HttpStatus.NOT_FOUND);
		} else {
			teste.setId(testeID);
			TR.save(teste);
			return new ResponseEntity<>("Teste edited", HttpStatus.OK);
		}

	}

	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> TesteDelete(@PathVariable long probID, @PathVariable long testID) {
		if (!PR.exists(probID)) {
			return new ResponseEntity<>("Problema with ID " + probID + " not found", HttpStatus.NOT_FOUND);
		} else if (!TR.exists(testID)) {
			return new ResponseEntity<>("Teste with ID " + testID + " not found", HttpStatus.NOT_FOUND);
		}
		TR.delete(testID);
		return ResponseEntity.ok("Test " + testID + " deleted");
	}

}
