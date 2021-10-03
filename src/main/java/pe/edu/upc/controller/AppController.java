package pe.edu.upc.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.Role;
import pe.edu.upc.model.User;
import pe.edu.upc.service.UserService;
import pe.edu.upc.serviceimpl.CustomUserDetails;

@Controller

public class AppController {

	@Autowired
	private UserService service;
	

	@RequestMapping("/bienvenido")
	public String irDuenoBienvenido() {
		return "bienvenido";		
	}	
	@RequestMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		
		return "signup_form";
	}
	
	@RequestMapping("/process_register")
	public String processRegister(User user,RedirectAttributes redirectAttributes) {
		service.registerDefaultUser(user);
		redirectAttributes.addFlashAttribute("success",true);
		return "redirect:/users";
	}
	
	@GetMapping("/users")
	public String listUsers(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users";
	}
	
	@GetMapping("/users/edit/{id}")
	public String editUser(@PathVariable("id") Long id, Model model) {
		User user = service.get(id);
		List<Role> listRoles = service.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user) {
		service.save(user);
		
		return "redirect:/users";
	}
	@RequestMapping("/users/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id,RedirectAttributes redirectAttributes) {
		if (id!=null && id>0) {
			service.eliminar(id);
			List<User> listUsers = service.listAll();
			redirectAttributes.addFlashAttribute("warning",true);
			model.put("listUsers", listUsers);
		}
		return "redirect:/users";
	}
	@GetMapping("/Miperfil")
	public String Miperfil(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		
		model.addAttribute("user",user);
		return "Miperfil";
	}
	

}