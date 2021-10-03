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

import pe.edu.upc.model.RitmoCardiaco;
import pe.edu.upc.resource.SaveRitmoCardiacoResource;
import pe.edu.upc.resource.RitmoCardiacoResource;
import pe.edu.upc.service.RitmoCardiacoService;


@RestController
@CrossOrigin
@RequestMapping("/ritmo")
public class RitmoCardiacoController {
	@Autowired
    private ModelMapper mapper;

    @Autowired
    private RitmoCardiacoService RitmoCardiacoService;

    @PostMapping("/mobile/{mobileId}/RitmoCardiaco")
    public RitmoCardiacoResource createRitmoCardiaco(@PathVariable(name = "mobileId") int mobileId, @Valid @RequestBody SaveRitmoCardiacoResource resource) {
        return convertToResource(RitmoCardiacoService.createRitmoCardiaco(mobileId, convertToEntity(resource)));
    }

    @GetMapping("/mobile/{mobileId}/RitmoCardiaco")
    public Page<RitmoCardiacoResource> getAllEmergenciesByPatientId(@PathVariable(name = "mobileId") int mobileId, Pageable pageable) {
        Page<RitmoCardiaco> RitmoCardiacoPage = RitmoCardiacoService.getAllRitmoCardiacoByPatientId(mobileId, pageable);
        List<RitmoCardiacoResource> resources = RitmoCardiacoPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @GetMapping("/mobile/{mobileId}/RitmoCardiaco/{RitmoCardiacoId}")
    public RitmoCardiacoResource getRitmoCardiacoByIdAndPatientId(@PathVariable(name = "mobileId") int mobileId, @PathVariable(name = "RitmoCardiacoId") int RitmoCardiacoId) {
        return convertToResource(RitmoCardiacoService.getRitmoCardiacoByIdAndPatientId(mobileId, RitmoCardiacoId));
    }

    private RitmoCardiaco convertToEntity(SaveRitmoCardiacoResource resource){
        return mapper.map(resource, RitmoCardiaco.class);
    }

    private RitmoCardiacoResource convertToResource(RitmoCardiaco entity){
        return mapper.map(entity, RitmoCardiacoResource.class);
    }
}
