package bootwildfly;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import models.Solucao;

@RestController
public class SolucaoController {

	@Autowired
	SolucaoRepository SR;

	@Autowired
	ProblemaRepository PR;

	// Accept last as variable to find the last Solucao to Problema
	@RequestMapping(path = "Solucao", method = RequestMethod.GET)
	public List<Solucao> Solucao(@RequestParam(required = false) boolean last) {

		List<Solucao> sols;
		if (last) {
			sols = SR.findByLast(last);
		} else {
			sols = SR.findAll();
		}

		return sols;
	}

	// Resource to find specific solutionS to the provided probID
	@RequestMapping(path = "Problema/{probID}/Solucao", method = RequestMethod.GET)
	public ResponseEntity<?> Solucao2SpecificProblem(@PathVariable long problemID) {

		if (!PR.exists(problemID)) {
			return new ResponseEntity<>("No Problem found with id " + problemID, HttpStatus.NOT_FOUND);
		}

		List<Solucao> sols = SR.findByProblemID(problemID);
		if (sols.size() == 0) {
			return new ResponseEntity<>("No Solution for the specified Problem", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(sols, HttpStatus.OK);

	}

	@RequestMapping(path = "Solucao", method = RequestMethod.POST)
	public ResponseEntity<?> Solucao(@Valid @RequestBody Solucao sol, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return ResponseEntity.badRequest().body("The object contain errors");
		}

		// Find the other solutions for the same Problema and set last = false
		List<Solucao> temp_sols = SR.findByProblemIDAndLast(sol.getId(), true);
		for (Solucao solucao : temp_sols) {
			solucao.setLast(false);
			SR.save(solucao);
		}

		SR.save(sol);
		return ResponseEntity.ok("Solucao saved");
	}

	// ---------------- SOLUCAO/ID (C)RUD ----------------

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.GET)
	public ResponseEntity<?> SolucaoSpecific(@PathVariable long solID) {

		if (!SR.exists(solID)) {
			return new ResponseEntity<>("No solution found with id " + solID, HttpStatus.NOT_FOUND);
		}

		Solucao sol = SR.findById(solID);
		return new ResponseEntity<>(sol, HttpStatus.OK);
	}

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.PUT)
	public ResponseEntity<?> SolucaoPut(@PathVariable long solID, @Valid @RequestBody Solucao sol,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<>("The object contain errors", HttpStatus.BAD_REQUEST);
		} else if (!SR.exists(solID)) {
			return new ResponseEntity<>("Solution with ID " + solID + " not found", HttpStatus.NOT_FOUND);
		} else {
			sol.setId(solID);
			SR.save(sol);
			return new ResponseEntity<>("Solucao edited", HttpStatus.OK);
		}

	}

	@RequestMapping(path = "Solucao/{solID}", method = RequestMethod.DELETE)
	public ResponseEntity<?> SolucaoDelete(@PathVariable long solID) {
		if (!SR.exists(solID)) {
			return new ResponseEntity<>("Solucao with ID " + solID + " not found", HttpStatus.NOT_FOUND);
		}
		SR.delete(solID);
		return ResponseEntity.ok("Solution " + solID + " deleted");
	}

}
