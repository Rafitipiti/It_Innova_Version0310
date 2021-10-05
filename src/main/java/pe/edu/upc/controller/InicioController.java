package pe.edu.upc.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pe.edu.upc.algorithm.algorithmService;

@EnableScheduling
@Controller
@RequestMapping("/inicio")
public class InicioController {
	@Autowired
	@Qualifier("algorithmServicePython")
	private algorithmService service;

	@RequestMapping("/bienvenido")
	public String irBienvenido() {
		
		return "bienvenido";
	}

	@RequestMapping("/login")
	public String irLogin() {
		return "login";
	}
	@RequestMapping("/logout")
	 public String irLogOut() {
			
			return "logout";
		} 
	@RequestMapping("/Miperfil")
	public String Miperil() {
		
		return "Miperfil";
	}
	
	//@RequestMapping("/**")
	//public String index() {
		//return service.getalgorithm();
	//}
}
