package pe.edu.upc.service;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.RitmoCardiaco;

public interface RitmoCardiacoService {
	
	  RitmoCardiaco createRitmoCardiaco(int userId, RitmoCardiaco course);
	  RitmoCardiaco getRitmoCardiacoByIdAndPatientId(int RitmoCardiacoId, int patientId);
	  Page<RitmoCardiaco> getAllRitmoCardiacoByPatientId(int RitmoCardiacoId, Pageable pageable);
      public Optional<RitmoCardiaco> listarId(int RitmoCardiacoId);
	  List<RitmoCardiaco> listar();
	    List<RitmoCardiaco> findByKeyword(String keyword);
	 
}
