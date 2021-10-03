package pe.edu.upc.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.model.Emergency;
import pe.edu.upc.model.Paciente;
@Repository
public interface IPacienteDAO extends JpaRepository<Paciente, Integer> {
	@Query(value = "select g from Paciente as g where g.nroDocumento like %:keyword%")
	List<Paciente> buscarPaciente(@Param("keyword")String keyword);
	

}
