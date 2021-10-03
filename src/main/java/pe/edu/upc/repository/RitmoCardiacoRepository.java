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
@Repository
public interface RitmoCardiacoRepository extends JpaRepository<RitmoCardiaco, Integer> {
	 Page<RitmoCardiaco> findByPatientId(int patientId, Pageable pageable);
	    Optional<RitmoCardiaco> findByIdAndPatientId(int RitmoCardiacoId, int patientId);
	    @Query(value = "select e from RitmoCardiaco as e where e.patient.dni like %:keyword% ")
	    List<RitmoCardiaco> findByKeyword(@Param("keyword") String keyword);


}
