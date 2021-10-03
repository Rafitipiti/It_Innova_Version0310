package pe.edu.upc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.Paciente;
import pe.edu.upc.repository.EmergencyRepository;
import pe.edu.upc.repository.MovPatientRepository;
import pe.edu.upc.service.EmergencyService;

import java.util.List;
import java.util.Optional;

@Service
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    private EmergencyRepository emergencyRepository;
   

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
}
