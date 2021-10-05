package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pe.edu.upc.algorithm.algorithmService;
import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.Paciente;
import pe.edu.upc.service.EmergencyService;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class EmergencyController {
	
    @Autowired
    private EmergencyService emergencyService;

    @RequestMapping("/historial")
    public String historial(Model model, String keyword) {
        List<Emergency> emergencies = emergencyService.listar();

        if(keyword != null){
            model.addAttribute("emergencies", emergencyService.findByKeyword(keyword));
        }else
            model.addAttribute("emergencies",emergencies);

        return "historial";
    }
    
    
    @RequestMapping("/listar")
    public String listar(Model model) {
        List<Emergency> emergencies = emergencyService.listar();
        for(int i = 0; i < emergencies.size(); i++){
            if(emergencies.get(i).getState() == 1){
                emergencies.remove(i);
                i--;
            }
        }
        model.addAttribute("emergencies",emergencies);
        return "listEmergencia";
    }

    @RequestMapping("/listarat")
    public String listarAtendidos(Model model) {
        List<Emergency> emergencies2 = emergencyService.listar();
        for(int i = 0; i < emergencies2.size(); i++){
            if(emergencies2.get(i).getState() == 0){
                emergencies2.remove(i);
                i--;
            }
        }

        model.addAttribute("emergenciesAtt",emergencies2);
        return "listEmergenciaAtendida";
    }

    @RequestMapping("/detalle/{id}")
    public String detalle (@PathVariable int id, Model model, RedirectAttributes objRedir) {
        List<Emergency> emergencies2 = emergencyService.listar();
        for(int i = 0; i < emergencies2.size(); i++){
            if(emergencies2.get(i).getId() != id){
                emergencies2.remove(i);
                i--;
            }
        }
        Optional<Emergency> emergency = emergencyService.listarId(id);
        if (emergency.isEmpty()) {
            objRedir.addFlashAttribute("mensaje", "ocurrio un error");
            return "redirect:/patient/listar";
        }
        else {
            model.addAttribute("emergencies",emergencies2);
            return "detallePaciente";

        }
    }

    @RequestMapping("/modificar/{id}")
    public String modificar (@PathVariable int id, Model model, RedirectAttributes objRedir) {
        List<Emergency> emergencies2 = emergencyService.listar();
        for(int i = 0; i < emergencies2.size(); i++){
            if(emergencies2.get(i).getState() == 1){
                emergencies2.remove(i);
                i--;
            }
        }
        Optional<Emergency> objEmergency = emergencyService.listarId(id);
        if (objEmergency.isEmpty()) {
            objRedir.addFlashAttribute("mensaje", "ocurrio un error");
            return "redirect:/patient/listar";
        }
        else {
            for(int i = 0; i < emergencies2.size(); i++){
                if(emergencies2.get(i).getId() == id){
                    emergencyService.modificar(emergencies2.get(i));
                }
            }
            model.addAttribute("emergencies",emergencies2);
            return "redirect:/patient/listar";

        }
    }
}
