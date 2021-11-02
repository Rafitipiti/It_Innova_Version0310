package pe.edu.upc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.resource.ChangeDataResource;
import pe.edu.upc.service.MovPatientService;

@Controller
public class ChangeDataAppController {
    @Autowired
    private MovPatientService MovPService;

    @PostMapping("/change_datax")
    @ResponseBody
    public void changeDataApp(@RequestBody ChangeDataResource changeData, Model model) {
        int id = changeData.Get_id();
        System.out.println(id);
        MovPatient customer = MovPService.getUserById(id);

        String name = changeData.Get_name();
        String lastName = changeData.Get_lastName();
        String email = changeData.Get_email();
        String password = changeData.Get_password();

        System.out.println(name);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(password);

        MovPatient customerTemp = MovPService.getUserByEmail(email);
        System.out.println(customerTemp.getName());


        if (customerTemp.getName()==null || customer.getEmail().equals(email)){
            System.out.println("ESTAMOS BIEN");
            customer.setName(name);
            customer.setLastName(lastName);
            customer.setEmail(email);
            customer.setPassword(password);
            MovPService.update_change_data_app(customer);
            throw new ResponseStatusException(HttpStatus.OK);
        }else {
            System.out.println("ESTAMOS MAL :( ");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }


    }

}
