package pe.edu.upc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.model.Emergency;
import pe.edu.upc.repository.EmergencyRepository;
import pe.edu.upc.repository.MovPatientRepository;
import pe.edu.upc.service.EmergencyService;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    private EmergencyRepository emergencyRepository;
    @Autowired
    private MovPatientRepository patientRepository;

    @Override
    public boolean modificar(Emergency emergency) {
        boolean flag = false;
        try {
            emergency.setState(1);
            emergencyRepository.save(emergency);
            flag = true;
        }
        catch(Exception ex) {
            System.out.println("Sucedio un roche");
        }
        return flag;
    }

    @Override
    public Optional<Emergency> listarId(int emergencyId){
        return emergencyRepository.findById(emergencyId);
    }

    @Override
    public List<Emergency> listar() {
        return emergencyRepository.findAll();
    }

    @Override
    public List<Emergency> findByKeyword(String keyword) {
        return emergencyRepository.findByKeyword(keyword);
    }


    @Override
    public Emergency createEmergency(int userId, Emergency em) {
        Emergency newEme = new Emergency();
        return patientRepository.findById(userId).map(user -> {

            return emergencyRepository.save(em);

        }).orElseThrow(() -> new ResourceNotFoundException("Patient", "Id", userId));


    }

    @Override
    public Page<Emergency> getAllEmergenciesByPatientId(int emergencyId, Pageable pageable) {
        return emergencyRepository.findByPatientId(emergencyId, pageable);
    }

    @Override
    public Emergency updateEmergency(int patientId, int emergencyId, Emergency emergencyDetail) {
        if(!patientRepository.existsById(patientId))
            throw new ResourceNotFoundException("Patient", "Id", patientId);

        return emergencyRepository.findById(emergencyId).map(em -> {
            em.setState(1);
            return emergencyRepository.save(em);
        }).orElseThrow(() -> new ResourceNotFoundException("Emergency", "Id", emergencyId));
    }

    @Override
    public Emergency getEmergencyByIdAndPatientId(int emergencyId, int patientId) {
        return emergencyRepository.findByIdAndPatientId(emergencyId, patientId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course not found with Id" + emergencyId +
                                "and PatientId " + patientId
                ));
    }

}
