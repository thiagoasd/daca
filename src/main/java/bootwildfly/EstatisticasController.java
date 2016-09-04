package bootwildfly;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class EstatisticasController {
	ProblemaRepository PR;

	@RequestMapping(path = "Estatisticas", method = RequestMethod.GET)
	public ResponseEntity<?> Estatisticas() {

		return ResponseEntity.ok("");
	}
}
