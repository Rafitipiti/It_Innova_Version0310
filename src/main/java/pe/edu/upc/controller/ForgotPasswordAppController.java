package pe.edu.upc.controller;

import net.bytebuddy.utility.RandomString;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.model.Forgot;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.service.MovPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class ForgotPasswordAppController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private MovPatientService MovPService;

    private String randomcode = create_random();
    
    public String create_random() {
    	String code = "";
    	char c = 'a';
    	for(int i = 0; i < 10; i++) {
    		int ran = ThreadLocalRandom.current().nextInt(0,36);
    		ran = ran%36;	
    		if(ran >= 10) {
    			ran = ran+55;
    			c = (char)ran;
    		}
    		else {
    			c = (char)(ran+'0');
    		}
    		code = code + c; 		
        }
    	return code;
    }
    
    @PostMapping("/forgot_password_app")
    @ResponseStatus(value = HttpStatus.OK)
    public void processForgotAppPassword(@RequestBody Forgot forgot, Model model) {
        String email = forgot.Get_email();
        String token = RandomString.make(30);
        System.out.println(forgot.email);

        MovPatient customer = MovPService.getUserByEmail(email);
        String name = customer.getName();
        if (name == null ) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            try {
                System.out.println(name);
                sendEmailApp(email);
                //MovPService.updateResetPasswordToken(token, email);      this
                model.addAttribute("message", "Hemos enviado un enlace para restablecer la contrase?a a su correo electronico. Por favor, compruebe.");
                System.out.println("SE HA ENVIADO UN MENSAJE AL CORREO");
            } catch (Exception ex) {
                model.addAttribute("error", ex.getMessage());
                System.out.println("TUVIMOS UN ERROR");
            }
            throw new ResponseStatusException(HttpStatus.OK);
        }
    }

    public void sendEmailApp(String recipientEmail)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("it.innova.service@gmail.com", "IT Innova Support");
        helper.setTo(recipientEmail);
        String subject = "Aqui? esta su codigo de verificacion";

        String content = "<p>Hola,</p>"
                + "<p>Ha solicitado restablecer su contrase?a.</p>"
                + "<p>El codigo de verificacion es el siguiente:</p>"
                + randomcode
                + "<br>"
                + "<p>Ingreselo en la aplicacion y procesa con los pasos para el restablecimiento de su contrase?a, ";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @PostMapping("/reset_password_app")
    @ResponseStatus(value = HttpStatus.OK)
    public void processResetAppPassword(@RequestBody Forgot forgot, Model model) {
        String token = forgot.Get_token();
        String password = forgot.Get_password();
        String email = forgot.Get_email();
        System.out.println(forgot.token);
        System.out.println(forgot.password);
        System.out.println(forgot.email);
        
        MovPatient customer = MovPService.getUserByEmail(email);
        String name = customer.getName();
        model.addAttribute("title", "Reset your password");
        
        if (name == null || !((token).equals(randomcode))) {
            model.addAttribute("message", "Invalid Code");
            System.out.println(customer);
            System.out.println("TUVIMOS UN ERROR");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        } else {
            System.out.println(customer);
            MovPService.updatePassword_app(customer,password);

            model.addAttribute("message", "You have successfully changed your password.");
            System.out.println("SE CAMBIO LA CONTRASENA EXITOSAMENTE");
            throw new ResponseStatusException(HttpStatus.OK);
        }
    }
}