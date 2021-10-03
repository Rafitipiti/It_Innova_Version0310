package pe.edu.upc.service;
import java.util.List;
import java.util.Optional;

import pe.edu.upc.model.Paciente;
public interface IPacienteService {
	public boolean insertar(Paciente paciente);
	public boolean modificar(Paciente paciente);
	public void eliminar(int idPaciente);
	public Optional<Paciente> listarId(int idPaciente);
	List<Paciente> listar();
	List<Paciente> buscarPaciente(String nroDocumento);
}
