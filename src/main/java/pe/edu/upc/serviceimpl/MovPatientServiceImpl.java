package pe.edu.upc.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.exception.ResourceNotFoundException;
import pe.edu.upc.model.MovPatient;
import pe.edu.upc.repository.MovPatientRepository;
import pe.edu.upc.service.MovPatientService;

@Service
public class MovPatientServiceImpl implements MovPatientService {

    @Autowired
    private MovPatientRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public MovPatient createUser(MovPatient user) {
        MovPatient newUser = new MovPatient();
        java.util.Date date=new java.util.Date();
        newUser.setName(user.getName());
        newUser.setLastName(user.getLastName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());
        newUser.setDni(user.getDni());
        newUser.setDateCreation(date);
        newUser.setDateLocation(date);


        return userRepository.save(newUser);
    }

    @Override
    public MovPatient getUserById(int userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with Id: "+userId));
    }

    @Override
    public Page<MovPatient> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public MovPatient updateUser(int  userId, MovPatient userRequest) {
        return null;
    }

    @Override
    public MovPatient changePassword(int  userId, MovPatient userRequest) {
        MovPatient user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User not found with Id: "+userId));
        user.setName(userRequest.getName());
        user.setLastName(user.getLastName());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setDni(user.getDni());
        user.setDateCreation(user.getDateCreation());
        user.setDateLocation(user.getDateLocation());

        return userRepository.save(user);
    }

    @Override
    public ResponseEntity<?> deleteUser(int  userId) {
        return userRepository.findById(userId).map(user -> {
            userRepository.delete(user);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "User not found with Id: " + userId));
    }

    @Override
    public MovPatient getUserByEmail(String email) {
        MovPatient myuser = userRepository.findByEmail(email);
        if (myuser == null){
            MovPatient paciente =  new MovPatient();
            return paciente;
        }
        else {
            return myuser;
        }
    }
}
