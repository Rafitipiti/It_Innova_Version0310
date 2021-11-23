package pe.edu.upc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pe.edu.upc.model.Emergency;

import java.util.List;
import java.util.Optional;

public interface EmergencyService {

    public Optional<Emergency> listarId(int emergencyId);
    public boolean modificar(Emergency emergency);
    List<Emergency> listar();
    List<Emergency> findByKeyword(String keyword);
    Emergency createEmergency(int userId, Emergency em);
    Emergency updateEmergency(int patientId, int emergencyId, Emergency emergencyDetail);
    Emergency getEmergencyByIdAndPatientId(int emergencyId, int patientId);
    Page<Emergency> getAllEmergenciesByPatientId(int emergencyId, Pageable pageable);
}
