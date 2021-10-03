package pe.edu.upc.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.RitmoCardiaco;
import pe.edu.upc.model.Ubicacion;



@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion, Integer> {
	 Page<Ubicacion> findByPatientId(int patientId, Pageable pageable);
	    Optional<Ubicacion> findByIdAndPatientId(int UbicacionId, int patientId);
	    @Query(value = "select e from Ubicacion as e where e.patient.dni like %:keyword% ")
	    List<Ubicacion> findByKeyword(@Param("keyword") String keyword);
}
