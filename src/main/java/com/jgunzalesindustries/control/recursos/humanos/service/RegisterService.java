package main.java.com.jgunzalesindustries.control.recursos.humanos.service;

import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request.RegisterDTORequest;
import main.java.com.jgunzalesindustries.control.recursos.humanos.model.User;
import main.java.com.jgunzalesindustries.control.recursos.humanos.repository.UserRepository;
import main.java.com.jgunzalesindustries.control.recursos.humanos.security.jbcrypt.BCrypt;



public class RegisterService {

    private final UserRepository userRepository;

    public RegisterService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(RegisterDTORequest request) {

        validate(request);

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un usuario registrado con ese email.");
        }

       
        String passwordHash = BCrypt.hashpw(request.getPassword(), BCrypt.gensalt());
        User user = new User(
                userRepository.generateUserId(),
                request.getName(),
                request.getLastname(),
                request.getEmail(),
                passwordHash,            // se guarda el HASH
                request.getIdRol());

        if (!userRepository.save(user)) {
            throw new RuntimeException("No se pudo registrar el usuario.");
        }
    }

    private void validate(RegisterDTORequest r) {
        if (r == null) {
            throw new IllegalArgumentException("Los datos de registro están vacíos.");
        }
        if (isBlank(r.getName())) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (isBlank(r.getLastname())) {
            throw new IllegalArgumentException("El apellido no puede estar vacío.");
        }
        if (isBlank(r.getEmail()) || !r.getEmail().contains("@")) {
            throw new IllegalArgumentException("El email no es válido.");
        }
        if (isBlank(r.getPassword()) || r.getPassword().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres.");
        }
        if (!r.getPassword().equals(r.getConfirmPassword())) { 
            throw new IllegalArgumentException("Las contraseñas no coinciden.");
        }
        if (r.getIdRol().isBlank()) {
            throw new IllegalArgumentException("Debes seleccionar un rol válido.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
