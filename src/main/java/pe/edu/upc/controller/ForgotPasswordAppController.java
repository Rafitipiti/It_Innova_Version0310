package pe.edu.upc.controller;

import net.bytebuddy.utility.RandomString;
import pe.edu.upc.model.User;
import pe.edu.upc.security.Utility;
import pe.edu.upc.service.EmailSenderService;
import pe.edu.upc.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.ThreadLocalRandom;

@Controller
public class ForgotPasswordAppController {
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private UserService userService;

    @GetMapping("/forgot_password_app")
    public String showForgotAppPasswordForm() {
        return "forgot_password_form";
    }

    @PostMapping("/forgot_password_app")
    @ResponseStatus(value = HttpStatus.OK)
    public void processForgotAppPassword(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String token = RandomString.make(30);

        try {
            sendEmailApp(email);
            userService.updateResetPasswordToken(token, email);
            model.addAttribute("message", "Hemos enviado un enlace para restablecer la contraseña a su correo electronico. Por favor, compruebe.");

        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        //System.out.println("DEBUGGEOOOOOOOOOOOOOOOOOOOO");
    }

    public void sendEmailApp(String recipientEmail)
            throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        
        helper.setFrom("it.innova.service@gmail.com", "Shopme Support");
        helper.setTo(recipientEmail);
        int randomNum = ThreadLocalRandom.current().nextInt(11111, 99999 + 1);
        String subject = "Aqui­ esta su codigo de verificacion";

        String content = "<p>Hola,</p>"
                + "<p>Ha solicitado restablecer su contraseña.</p>"
                + "<p>El codigo de verificacion es el siguiente:</p>"
                + randomNum
                + "<br>"
                + "<p>Ingreselo en la aplicacion y procesa con los pasos para el restablecimiento de su contraseña, ";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }


    @GetMapping("/reset_password_app")
    public String showResetAppPasswordForm(@Param(value = "token") String token, Model model) {
        User customer = userService.getByResetPasswordToken(token);
        model.addAttribute("token", token);

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        }

        return "reset_password_form";
    }

    @PostMapping("/reset_password_app")
    public String processResetAppPassword(HttpServletRequest request, Model model) {
        String token = request.getParameter("token");
        String password = request.getParameter("password");

        User customer = userService.getByResetPasswordToken(token);
        model.addAttribute("title", "Reset your password");

        if (customer == null) {
            model.addAttribute("message", "Invalid Token");
            return "message";
        } else {
            userService.updatePassword(customer, password);

            model.addAttribute("message", "You have successfully changed your password.");
        }

        return "login";
    }
}