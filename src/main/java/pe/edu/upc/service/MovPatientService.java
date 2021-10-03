package pe.edu.upc.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pe.edu.upc.model.MovPatient;

public interface MovPatientService {
    MovPatient createUser(MovPatient user);
    MovPatient getUserById(int userId);
    Page<MovPatient> getAllUsers(Pageable pageable);
    MovPatient updateUser(int  userId, MovPatient userRequest);
    MovPatient changePassword(int  userId, MovPatient userRequest);
    ResponseEntity<?> deleteUser(int userId);

    MovPatient getUserByEmail(String email);
}
