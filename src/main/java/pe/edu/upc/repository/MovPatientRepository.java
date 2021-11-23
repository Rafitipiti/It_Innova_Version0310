package pe.edu.upc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.model.MovPatient;

@Repository
public interface MovPatientRepository extends JpaRepository<MovPatient, Integer> {
	@Query("SELECT u FROM MovPatient u WHERE u.email = ?1")
	public MovPatient findByEmail(String email);
	public MovPatient findByResetPasswordToken(String token);
	
}
