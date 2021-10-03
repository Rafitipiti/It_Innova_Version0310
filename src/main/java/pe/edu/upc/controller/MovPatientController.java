package pe.edu.upc.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.resource.MovPatientResource;
import pe.edu.upc.resource.SaveMovPatientResource;
import pe.edu.upc.service.MovPatientService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RestController
@CrossOrigin
@RequestMapping("/api")
public class MovPatientController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private MovPatientService patientService;

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

}
