package pe.edu.upc.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.model.Paciente;
import pe.edu.upc.repository.IPacienteDAO;
import pe.edu.upc.service.IPacienteService;

@Service
public class PacienteServiceImpl implements IPacienteService {

	@Autowired
	private IPacienteDAO dPaciente;
	
	@Override
	public boolean insertar(Paciente paciente) {
		Paciente objPaciente = dPaciente.save(paciente);
		if(objPaciente == null) {
			return false;
		}else {
			return true;
		}
	}
	@Override
	public boolean modificar(Paciente paciente) {
		boolean flag = false;
		try {
			dPaciente.save(paciente);
			flag = true;
		}
		catch(Exception ex) {
			System.out.println("Sucedio un roche");
		}
		return flag;
	}
	@Override
	public void eliminar(int idPaciente) {
		dPaciente.deleteById(idPaciente);
	}
	@Override
	public Optional<Paciente> listarId(int idPaciente){
		return dPaciente.findById(idPaciente);
	}
	@Override
	public List<Paciente> listar(){
		return dPaciente.findAll();
	}
	@Override
	public List<Paciente> buscarPaciente(String nombreMedico){
		return dPaciente.buscarPaciente(nombreMedico);
	}

}
