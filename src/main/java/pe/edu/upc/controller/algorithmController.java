package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upc.algorithm.algorithmService;


@EnableScheduling
@RestController
public class algorithmController {

	@Autowired
	@Qualifier("algorithmServicePython")
	private algorithmService service;

	@Scheduled(fixedRate=2000)
	public String index() {
		if (service.getalgorithm() == "1") {
			System.out.print("Se acaba de ingresar un registro");
			return "listEmergencia";
		}
		return "";
	}

}
