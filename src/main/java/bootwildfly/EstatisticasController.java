package bootwildfly;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import models.Estatisticas;

@RestController
public class EstatisticasController {
	
	@Autowired
	ProblemaRepository PR;
	
	@Autowired
	SolucaoRepository SR;
	
	@Autowired
	TesteRepository TR;
	
	@RequestMapping(path = "Estatisticas", method = RequestMethod.GET)
	public ResponseEntity<?> Estatisticas() {
		long problemaNum = PR.count();
		long testeNum = TR.count();
		long solucaoNum = SR.count();
		
		Estatisticas stats = new Estatisticas(problemaNum, solucaoNum, testeNum);	
		return ResponseEntity.ok(stats);
	}
}
