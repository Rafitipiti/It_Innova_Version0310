package pe.edu.upc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import pe.edu.upc.model.RitmoCardiaco;
import pe.edu.upc.model.Ubicacion;

public interface UbicacionService {
	Ubicacion createUbicacion(int userId, Ubicacion course);
    Ubicacion getUbicacionByIdAndPatientId(int UbicacionId, int patientId);
    Page<Ubicacion> getAllUbicacionByPatientId(int UbicacionId, Pageable pageable);
    List<Ubicacion> findByKeyword(String keyword);


    public Optional<Ubicacion> listarId(int UbicacionId);

    List<Ubicacion> listar();

}
