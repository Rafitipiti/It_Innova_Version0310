package pe.edu.upc.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.upc.model.Ubicacion;
import pe.edu.upc.resource.UbicacionResource;
import pe.edu.upc.resource.SaveUbicacionResource;
import pe.edu.upc.service.UbicacionService;

@RestController
@CrossOrigin
@RequestMapping("/ubicacion")
public class UbicacionController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UbicacionService ubicacionService;

    @PostMapping("/mobile/{mobileId}/ubicacion")
    public UbicacionResource createUbicacion(@PathVariable(name = "mobileId") int mobileId, @Valid @RequestBody SaveUbicacionResource resource) {
        return convertToResource(ubicacionService.createUbicacion(mobileId, convertToEntity(resource)));
    }

    @GetMapping("/mobile/{mobileId}/ubicacion")
    public Page<UbicacionResource> getAllEmergenciesByPatientId(@PathVariable(name = "mobileId") int mobileId, Pageable pageable) {
        Page<Ubicacion> UbicacionPage = ubicacionService.getAllUbicacionByPatientId(mobileId, pageable);
        List<UbicacionResource> resources = UbicacionPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/mobile/{mobileId}/ubicacion/{UbicacionId}")
    public UbicacionResource getUbicacionByIdAndPatientId(@PathVariable(name = "mobileId") int mobileId, @PathVariable(name = "UbicacionId") int UbicacionId) {
        return convertToResource(ubicacionService.getUbicacionByIdAndPatientId(mobileId, UbicacionId));
    }

    private Ubicacion convertToEntity(SaveUbicacionResource resource){
        return mapper.map(resource, Ubicacion.class);
    }

    private UbicacionResource convertToResource(Ubicacion entity){
        return mapper.map(entity, UbicacionResource.class);
    }
}
