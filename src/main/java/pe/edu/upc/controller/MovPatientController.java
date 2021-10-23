package pe.edu.upc.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import net.bytebuddy.utility.RandomString;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.resource.MovPatientResource;
import pe.edu.upc.resource.SaveMovPatientResource;
import pe.edu.upc.security.Utility;
import pe.edu.upc.service.EmailSenderService;
import pe.edu.upc.service.MovPatientService;
import pe.edu.upc.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/api")
public class MovPatientController {

    @Autowired
    private ModelMapper mapper;
    
	@Autowired //mail
	private EmailSenderService service; //mail
	
    @Autowired
    private MovPatientService patientService;
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/mobile/create")
    public MovPatientResource createUser(@Valid @RequestBody SaveMovPatientResource resource){
        MovPatient user = convertToEntity(resource);
        return convertToResource(patientService.createUser(user));
    }

    @GetMapping("/mobile")
    public Page<MovPatientResource> getAllUsers(Pageable pageable){
        Page<MovPatient> userPage = patientService.getAllUsers(pageable);
        List<MovPatientResource> resources = userPage.getContent()
                .stream().map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private MovPatient convertToEntity(SaveMovPatientResource resource){
        return mapper.map(resource, MovPatient.class);
    }

    private MovPatientResource convertToResource(MovPatient entity){
        return mapper.map(entity, MovPatientResource.class);
    }
    /*
    @PostMapping("/forgot_password_app")
    public String processForgotAppPassword(HttpServletRequest request, Model model) {	
        String email = request.getParameter("email");
        int randomNum = ThreadLocalRandom.current().nextInt(11111, 99999 + 1);
        String token = RandomString.make(30);     
        String body = "<p>Hola,</p>"
                + "<p>Ha solicitado restablecer su contraseña.</p>"
                + "<p>Haga clic en el enlace de abajo para cambiar su contraseña:</p>"
                + randomNum
                + "<br>"
                + "<p>Ignore este correo electronico si recuerda su contraseña, "
                + "o no ha realizado la solicitud.</p>";
        try {
            userService.updateResetPasswordToken(token, email);
            //String resetPasswordLink = Utility.getSiteURL(request) + "/reset_password?token=" + token;
            service.sendSimpleEmail(email, body, "Reset Password");
            //model.addAttribute("message", "Hemos enviado un enlace para restablecer la contraseña a su correo electronico. Por favor, compruebe.");

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        
        return "hi";
    }
    */
}
