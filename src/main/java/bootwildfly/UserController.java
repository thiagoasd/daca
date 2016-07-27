package bootwildfly;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	
	@RequestMapping(path = "Problema", method = RequestMethod.GET)
	public Problema Problema(String name, String codigo, String dica, String desc){
		Problema prob = new Problema(0, name, codigo, dica, desc);
		return prob;
	}

}
