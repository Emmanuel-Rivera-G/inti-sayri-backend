package pe.edu.utp.inti_sayri_backend.service;

import java.util.HashMap;
import java.util.Map;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.utp.inti_sayri_backend.model.User;
import pe.edu.utp.inti_sayri_backend.repository.UserRepository;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> registrarUsuario(User usuario) {
        Map<String, Object> response = new HashMap<>();
        
        Optional<User> usuarioExistente = userRepository.findByCorreoElectronico(usuario.getCorreoElectronico());
        
        if (usuarioExistente.isPresent()) {
            response.put("status", "error");
            response.put("message", "El correo ya está registrado.");
            return response;
        }
        
        userRepository.save(usuario);
        
        response.put("status", "success");
        response.put("message", "Registro exitoso.");
        response.put("data", usuario);
        return response;
    }
    
    public Map<String, Object> iniciarSesion(String correoElectronico, String contrasena) {
        Map<String, Object> response = new HashMap<>();
        
        Optional<User> usuario = userRepository.findByCorreoElectronico(correoElectronico);
        
        if (usuario.isPresent() && usuario.get().getContrasena().equals(contrasena)) {
            response.put("status", "success");
            response.put("message", "Inicio de sesión exitoso.");
        } else {
            response.put("status", "error");
            response.put("message", "Correo o contraseña incorrectos.");
        }
        
        return response;
    }
}
