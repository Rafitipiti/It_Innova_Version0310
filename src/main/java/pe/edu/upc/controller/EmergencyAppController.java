package pe.edu.upc.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.model.Emergency;
import pe.edu.upc.resource.EmergencyResource;
import pe.edu.upc.service.EmergencyService;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/emergencia")
public class EmergencyAppController {
    @Autowired
    private EmergencyService EmergencyService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/mobile/{mobileId}/Emergency")
    public Page<EmergencyResource> getAllEmergenciesByPatientId(@PathVariable(name = "mobileId") int mobileId, Pageable pageable) {
        pageable = Pageable.unpaged();
        Page<Emergency> EmergencyPage = EmergencyService.getAllEmergenciesByPatientId(mobileId, pageable);
        List<EmergencyResource> resources = EmergencyPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    private Emergency convertToEntity(EmergencyResource resource){
        return mapper.map(resource, Emergency.class);
    }

    private EmergencyResource convertToResource(Emergency entity){
        return mapper.map(entity, EmergencyResource.class);
    }
}