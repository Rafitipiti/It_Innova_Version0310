package pe.edu.upc;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.springframework.context.event.EventListener;
import pe.edu.upc.algorithm.algorithmService;
import pe.edu.upc.algorithm.algorithmServiceFactory;
import pe.edu.upc.service.EmailSenderService;
import java.util.concurrent.ThreadLocalRandom;


import java.util.Arrays;

@SpringBootApplication
public class Tp2V2Application { // extends SpringBootServletInitializer

    @Autowired //mail
    private EmailSenderService service; //mail

	public static void main(String[] args) {
		SpringApplication.run(Tp2V2Application.class, args);

	}

	@Bean
	public ModelMapper modelMapper() {return new ModelMapper();}
	
    @Bean(name = "algorithmServiceFactory")
    public algorithmServiceFactory algorithmFactory() {
    	return new algorithmServiceFactory();
    }
    
    @Bean(name = "algorithmServicePython")
    public algorithmService algorithmServicePython() throws Exception {
        return algorithmFactory().getObject();
    }

    @EventListener(ApplicationReadyEvent.class) //mail
    public void triggerMail(){ //mail
        int randomNum = ThreadLocalRandom.current().nextInt(11111, 99999 + 1);

        service.sendSimpleEmail("it.innova.service@gmail.com",String.valueOf(randomNum),"Reset Password"); //mail
    }
}
