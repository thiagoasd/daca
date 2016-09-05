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
import models.ResponseDTO;
import models.Teste;

@RestController
public class ProblemaConstroller {

	@Autowired
	ProblemaRepository PR;

	@Autowired
	TesteRepository TR;

	ResponseDTO response;

	// ---------------- PROBLEMA (CR)UD ----------------
	@RequestMapping(path = "Problema", method = RequestMethod.GET)
	public ResponseEntity<?> Problema() {

		List<Problema> probs = PR.findAll();
		return new ResponseEntity<>(probs, HttpStatus.OK);
	}

	@RequestMapping(path = "Problema", method = RequestMethod.POST)
	public ResponseEntity<?> Problema(@Valid @RequestBody Problema prob, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			response = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), false, "The object contain errors");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		PR.save(prob);
		response = new ResponseDTO(HttpStatus.OK.value(), true, prob.getId());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ---------------- PROBLEMA/ID C(RUD) ----------------
	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.GET)
	public ResponseEntity<?> ProblemaSpecific(@PathVariable long probID) {

		if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Problema found with id " + probID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		Problema prob = PR.findById(probID);
		return new ResponseEntity<>(prob, HttpStatus.OK);
	}

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.PUT)
	public ResponseEntity<?> ProblemaPut(@PathVariable long probID, @Valid @RequestBody Problema prob,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			response = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), false, "The object contain errors");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} else if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Problema found with id " + probID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else {
			prob.setId(probID);
			PR.save(prob);
			response = new ResponseDTO(HttpStatus.OK.value(), true, prob.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	@RequestMapping(path = "Problema/{probID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> ProblemaDelete(@PathVariable long probID) {

		if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false,
					"Problema with ID " + probID + " not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		PR.delete(probID);
		response = new ResponseDTO(HttpStatus.OK.value(), true, probID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ---------------- TESTE CRUD ----------------
	@RequestMapping(path = "Problema/{probID}/Teste", method = RequestMethod.GET)
	public ResponseEntity<?> Teste(@PathVariable long probID) {

		if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Problema found with id " + probID);
		} else {

			List<Teste> tests = TR.findByProblemID(probID);
			if (tests.size() == 0) {
				response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Teste found for this Problema");
			} else {
				return new ResponseEntity<>(tests, HttpStatus.OK);
			}
		}

		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(path = "Problema/{probID}/Teste", method = RequestMethod.POST)
	public ResponseEntity<?> Teste(@PathVariable long probID, @Valid @RequestBody Teste teste,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			response = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), false, "The object contain errors");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} else if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false,
					"Problema with ID " + probID + " not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else {
			TR.save(teste);
			Problema prob = PR.findById(probID);
			prob.addTest(teste);
			PR.save(prob);

			response = new ResponseDTO(HttpStatus.OK.value(), true, teste.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}

	// ---------------- TESTE/ID (C)RUD ----------------

	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.GET)
	public ResponseEntity<?> TesteSpecific(@PathVariable long probID, @PathVariable long testeID) {

		if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Problema found with id " + probID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (!TR.exists(testeID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Teste found with id " + testeID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		Teste teste = TR.findById(testeID);
		return new ResponseEntity<>(teste, HttpStatus.OK);

	}

	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.PUT)
	public ResponseEntity<?> TestePut(@PathVariable long probID, @Valid @PathVariable long testeID,
			@RequestBody Teste teste, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			response = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), false, "The object contain errors");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} else if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false,
					"Problema with ID " + probID + " not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else if (!TR.exists(testeID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "Teste with ID " + testeID + " not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else {
			teste.setId(testeID);
			TR.save(teste);
			response = new ResponseDTO(HttpStatus.OK.value(), true, testeID);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	}

	
	@RequestMapping(path = "Problema/{probID}/Teste/{testeID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> TesteDelete(@PathVariable long probID, @PathVariable long testeID) {
 		if (!PR.exists(probID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false,
					"Problema with ID " + probID + " not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		} else if (!TR.exists(testeID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "Teste with ID " + testeID + " not found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		
 		Problema prob = PR.findById(probID);
 		Teste test = TR.findById(testeID);
 		prob.removeTest(test);
 		PR.save(prob);
		TR.delete(testeID);
		response = new ResponseDTO(HttpStatus.OK.value(), true, testeID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
