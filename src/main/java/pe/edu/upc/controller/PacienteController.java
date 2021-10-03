package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.Paciente;
import pe.edu.upc.service.IPacienteService;

@Controller
@RequestMapping("/paciente")
public class PacienteController {

	@Autowired
	private IPacienteService gService;
	@RequestMapping("/bienvenido")
	public String irMedicoBienvenido() {
		return "bienvenido";		
	}
	
	@RequestMapping("/")
	public String irGenero(Map<String, Object> model) {
		model.put("listaPacientes", gService.listar());
		return "listPaciente";
	}
	
	@RequestMapping("/irRegistrar")
	public String irRegistrar(Model model) {
		model.addAttribute("paciente", new Paciente());
		return "paciente";
	}
	
	@RequestMapping("/registrar")
	public String tipodocumento(@ModelAttribute @Valid Paciente objPaciente, 
			BindingResult binRes, Model model,RedirectAttributes redirectAttributes ) throws ParseException 
	{
		if (binRes.hasErrors()) {
			return "paciente";
		}
		 else {
				List<Paciente> listaPacientes2;
				listaPacientes2 = gService.buscarPaciente(objPaciente.getNroDocumento().toString());
				List<Paciente>listaPacientes3;
				
				listaPacientes3 = gService.listar();
				List<Paciente> listaPacientes4;
				listaPacientes4 = gService.buscarPaciente(objPaciente.getNroDocumento().toString());
				if(objPaciente.getIdPaciente()>0)
					{
						for (int i = 0; i<listaPacientes3.size(); i++) 
						{
							if (listaPacientes4.isEmpty()) {
								
								gService.modificar(objPaciente);
								redirectAttributes.addFlashAttribute("info",true);
								return "redirect:/paciente/listar";
														}
							else {
								if(( objPaciente.getIdPaciente() ==listaPacientes3.get(i).getIdPaciente() && listaPacientes4.isEmpty()||(objPaciente.getIdPaciente()==listaPacientes2.get(i).getIdPaciente()))) {
									gService.modificar(objPaciente);
									redirectAttributes.addFlashAttribute("info",true);
									return "redirect:/paciente/listar";
									}
								else{
									redirectAttributes.addFlashAttribute("mensaje",true);//dni
									return "redirect:/paciente/irRegistrar";
								}
							}
						}
					}
				else {
					List<Paciente> listaPacientes;
					listaPacientes = gService.buscarPaciente(objPaciente.getNroDocumento().toString());
					if (listaPacientes.isEmpty()) {
						
						
						boolean flag = gService.insertar(objPaciente);
						
						if (flag) {
							redirectAttributes.addFlashAttribute("success",true);
							return "redirect:/paciente/listar";
						} else {
							redirectAttributes.addFlashAttribute("danger",true);//errors
							return "redirect:/paciente/irRegistrar";
						}
					}
						else {
							redirectAttributes.addFlashAttribute("mensaje",true);//dni
							return "redirect:/paciente/irRegistrar";
						}
					
						}
					}
					redirectAttributes.addFlashAttribute("danger",true);//errors
					return "redirect:/paciente/irRegistrar";

				
				}

	
	@RequestMapping("/actualizar")
	public String actualizar(@ModelAttribute @Valid Paciente objPaciente, 
			BindingResult binRes, Model model, RedirectAttributes objRedir ) 
					throws ParseException {
		if (binRes.hasErrors()) {
			return "redirect:/paciente/listar";
		}
		else {
			boolean flag = gService.modificar(objPaciente);
			if (flag) {
				objRedir.addFlashAttribute("mensaje", "se actualizo correctamente");
				return "redirect:/paciente/listar";
			}
			else {
				model.addAttribute("mensaje", "Ocurrio un roche");
				return "redirect:paciente/irRegistrar";
			}			
		}
	}
	
	@RequestMapping("/modificar/{id}")
	public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
		Optional<Paciente> objPaciente = gService.listarId(id);
		if (objPaciente == null) {
			objRedir.addFlashAttribute("mensaje", "ocurrio un error");
			return "redirect:/paciente/listar";
		}
		else {
			model.addAttribute("paciente", objPaciente);
			return "paciente";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				gService.eliminar(id);
				model.put("warning",true);//eliminar
				model.put("listaPacientes", gService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se puede eliminar un genero asignado");
			model.put("listaPacientes", gService.listar());
		}
		return "listPaciente";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaPacientes", gService.listar());
		return "listPaciente";
	}
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Paciente paciente) throws ParseException {
		gService.listarId(paciente.getIdPaciente());
		return "listPaciente";		
	}
	 @RequestMapping("/buscar")
	    public String historial(Model model, String keyword) {
	        List<Paciente> listaPacientes = gService.listar();

	        if(keyword != null){
	            model.addAttribute("listaPacientes", gService.buscarPaciente(keyword));
	        }else
	            model.addAttribute("listaPacientes", listaPacientes);

	        return "listPaciente";
	    }


}
