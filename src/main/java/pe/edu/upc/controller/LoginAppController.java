package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.model.Forgot;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.service.MovPatientService;

@Controller
public class LoginAppController {
    @Autowired
    private MovPatientService MovPService;

    @PostMapping("/login_app")
    public void loginApp(@RequestBody Forgot forgot, Model model) {
        String password = forgot.Get_password();
        String email = forgot.Get_email();

        MovPatient customer = MovPService.getUserByEmail(email);
        String realpassword = customer.getPassword();
        model.addAttribute("title", "Login");

        if (customer == null || Integer.parseInt(password) != Integer.parseInt(realpassword)) {
            forgot.flag = false;
            model.addAttribute("message", "Invalid Code");
            System.out.println(password+" es diferente de "+realpassword);
            System.out.println("TUVIMOS UN ERROR");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            forgot.flag = true;
            model.addAttribute("message", "You have successfully signed in.");
            System.out.println("SE INICIO SESION EXITOSAMENTE");
            throw new ResponseStatusException(HttpStatus.OK);
        }
    }
}
