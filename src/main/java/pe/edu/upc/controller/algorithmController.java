package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.algorithm.algorithmService;

@RestController
public class algorithmController {

	@Autowired
	@Qualifier("algorithmServicePython")
	private algorithmService service;

	@RequestMapping("/**")
	public String index() {
		return service.getalgorithm();
	}

}
