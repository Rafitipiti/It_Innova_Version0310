package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.model.RitmoCardiaco;
import pe.edu.upc.model.Ubicacion;
import pe.edu.upc.repository.MovPatientRepository;
import pe.edu.upc.repository.RitmoCardiacoRepository;
import pe.edu.upc.service.RitmoCardiacoService;
@Service
public class RitmoCardiacoServiceImpl implements RitmoCardiacoService {

    @Autowired
    private RitmoCardiacoRepository RitmoCardiacoRepository;
    @Autowired
    private MovPatientRepository patientRepository;


    @Override
    public RitmoCardiaco createRitmoCardiaco(int userId, RitmoCardiaco em) {
      	RitmoCardiaco newRitmo = new RitmoCardiaco();
        return patientRepository.findById(userId).map(user -> {
            java.util.Date date=new java.util.Date();
                newRitmo.setFecha(date);
                newRitmo.setRitmoCardiaco(em.getRitmoCardiaco());
                em.setPatient(user);
                return RitmoCardiacoRepository.save(em);
                
        }).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", userId));
   

    }

    @Override
    public RitmoCardiaco getRitmoCardiacoByIdAndPatientId(int RitmoCardiacoId, int patientId) {
        return RitmoCardiacoRepository.findByIdAndPatientId(RitmoCardiacoId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with Id" + RitmoCardiacoId +
                                "and PatientId " + patientId
                ));
    }

    @Override
    public Page<RitmoCardiaco> getAllRitmoCardiacoByPatientId(int RitmoCardiacoId, Pageable pageable) {
        return RitmoCardiacoRepository.findByPatientId(RitmoCardiacoId, pageable);
    }

   


    @Override
    public Optional<RitmoCardiaco> listarId(int RitmoCardiacoId){
        return RitmoCardiacoRepository.findById(RitmoCardiacoId);
    }

    @Override
    public List<RitmoCardiaco> listar() {
        return RitmoCardiacoRepository.findAll();
    }

    @Override
    public List<RitmoCardiaco> findByKeyword(String keyword) {
        return RitmoCardiacoRepository.findByKeyword(keyword);
    }
   
}
