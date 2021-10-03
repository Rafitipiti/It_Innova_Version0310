package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.model.RitmoCardiaco;
import pe.edu.upc.model.Ubicacion;
import pe.edu.upc.repository.MovPatientRepository;
import pe.edu.upc.repository.UbicacionRepository;
import pe.edu.upc.service.UbicacionService;
@Service
public class UbicacionServiceImpl implements UbicacionService {

    @Autowired
    private UbicacionRepository UbicacionRepository;
    @Autowired
    private MovPatientRepository patientRepository;


    @Override
    public Ubicacion createUbicacion(int userId, Ubicacion em) {

        return patientRepository.findById(userId).map(user -> {
        	java.util.Date date=new java.util.Date();
                em.setPatient(user);
                em.setLatitud(em.getLatitud());
                em.setLongitud(em.getLongitud());
                em.setFecha(date);
                return UbicacionRepository.save(em);
   
        }).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", userId));
    }

    @Override
    public Ubicacion getUbicacionByIdAndPatientId(int UbicacionId, int patientId) {
        return UbicacionRepository.findByIdAndPatientId(UbicacionId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with Id" + UbicacionId +
                                "and PatientId " + patientId
                ));
    }

    @Override
    public Page<Ubicacion> getAllUbicacionByPatientId(int UbicacionId, Pageable pageable) {
        return UbicacionRepository.findByPatientId(UbicacionId, pageable);
    }

   


    @Override
    public Optional<Ubicacion> listarId(int UbicacionId){
        return UbicacionRepository.findById(UbicacionId);
    }

    @Override
    public List<Ubicacion> listar() {
        return UbicacionRepository.findAll();
    }

    @Override
    public List<Ubicacion> findByKeyword(String keyword) {
        return UbicacionRepository.findByKeyword(keyword);
    }
    
}
