package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import pe.edu.upc.algorithm.algorithmService;


@EnableScheduling
@RestController
public class algorithmController {

	@Autowired
	@Qualifier("algorithmServicePython")
	private algorithmService service;

	
	//public RedirectView localRedirect() {
	  //  RedirectView redirectView = new RedirectView();
	    //redirectView.setUrl("redirect:/patient/listar");
	    //redirectView.setUrl("https://www.youtube.com/watch?v=wTowEKjDGkU");
	    //return redirectView;
	//}
	
	@RequestMapping("/**")
	@Scheduled(fixedRate=2000)
	public String index() {
		if (service.getalgorithm() == "1") {
			// COMANDO PARA ACTUALIZAR EL BROWSER
			//localRedirect();
			System.out.print("Se acaba de ingresar un registro");
		}
		return "";
	}

}
