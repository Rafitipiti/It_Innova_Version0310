package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.model.MovPatient;

@Repository
public interface MovPatientRepository extends JpaRepository<MovPatient, Integer> {
    MovPatient findByEmail(String email);
}
