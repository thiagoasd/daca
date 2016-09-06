package bootwildfly;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.ResponseDTO;
import models.Solucao;

@RestController
public class SolucaoController {

	@Autowired
	SolucaoRepository SR;

	@Autowired
	ProblemaRepository PR;
	ResponseDTO response;

	// Accept last as variable to find the last Solucao to Problema
	@RequestMapping(path = "Solucao", method = RequestMethod.GET)
	public ResponseEntity<?> Solucao(@RequestParam(required = false) boolean last) {

		List<Solucao> sols;
		if (last) {
			sols = SR.findByLast(last);
		} else {
			sols = SR.findAll();
		}

		return new ResponseEntity<>(sols, HttpStatus.OK);
	}

	// Resource to find specific solutionS to the provided probID
	@RequestMapping(path = "Problema/{probID}/Solucao", method = RequestMethod.GET)
	public ResponseEntity<?> Solucao2SpecificProblem(@PathVariable long problemID) {

		if (!PR.exists(problemID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Problem found with id " + problemID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		List<Solucao> sols = SR.findByProblemID(problemID);
		if (sols.size() == 0) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No Solution for the specified Problem");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(sols, HttpStatus.OK);

	}

	@RequestMapping(path = "Solucao", method = RequestMethod.POST)
	public ResponseEntity<?> Solucao(@Valid @RequestBody Solucao sol, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			response = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), false, "The object contain errors");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		if (!PR.exists(sol.getProblemID())) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false,
					"No Problem found with id " + sol.getProblemID());
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		// Find the other solutions for the same Problema and set last = false
		List<Solucao> temp_sols = SR.findByProblemIDAndLast(sol.getId(), true);
		for (Solucao solucao : temp_sols) {
			solucao.setLast(false);
			SolucaoSecureSave(solucao);
		}

		SolucaoSecureSave(sol);
		response = new ResponseDTO(HttpStatus.OK.value(), true, sol.getId());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	// ---------------- SOLUCAO/ID (C)RUD ----------------
	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.GET)
	public ResponseEntity<?> SolucaoSpecific(@PathVariable long solID) {

		if (!SR.exists(solID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No solution found with id " + solID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		Solucao sol = SR.findById(solID);
		return new ResponseEntity<>(sol, HttpStatus.OK);
	}

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.PUT)
	public ResponseEntity<?> SolucaoPut(@PathVariable long solID, @Valid @RequestBody Solucao sol,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			response = new ResponseDTO(HttpStatus.BAD_REQUEST.value(), false, "The object contain errors");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

		} else if (!SR.exists(solID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No solution found with id " + solID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

		} else {
			sol.setId(solID);
			SolucaoSecureSave(sol);
			response = new ResponseDTO(HttpStatus.OK.value(), true, sol.getId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

	}

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> SolucaoDelete(@PathVariable long solID) {
		if (!SR.exists(solID)) {
			response = new ResponseDTO(HttpStatus.NOT_FOUND.value(), false, "No solution found with id " + solID);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		SolucaoSecureDelete(solID);
		response = new ResponseDTO(HttpStatus.OK.value(), true, solID);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@Transactional
	private void SolucaoSecureSave(Solucao sol) {
		SR.save(sol);
	}

	@Transactional
	private void SolucaoSecureDelete(long id) {
		SR.delete(id);
	}

}
