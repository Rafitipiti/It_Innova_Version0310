package pe.edu.upc.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.model.User;

@Repository
public interface MovPatientRepository extends JpaRepository<MovPatient, Integer> {
	@Query("SELECT u FROM MovPatient u WHERE u.email = ?1")
	public MovPatient findByEmail(String email);
	public MovPatient findByResetPasswordToken(String token);
	
}
