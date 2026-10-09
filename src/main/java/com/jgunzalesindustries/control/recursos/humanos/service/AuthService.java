package main.java.com.jgunzalesindustries.control.recursos.humanos.service;

import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request.LoginDTORequest;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response.LoginDTOResponse;
import main.java.com.jgunzalesindustries.control.recursos.humanos.repository.AuthRepository;
import main.java.com.jgunzalesindustries.control.recursos.humanos.security.jbcrypt.BCrypt;


public class AuthService {

 
    private final AuthRepository authRepository;

    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }


    public LoginDTOResponse login(LoginDTORequest request) {

        if (request == null) {
            throw new RuntimeException("Los datos están vacíos.");
        }
        if (request.getEmail() == null || request.getPasswordHash()== null
                || request.getEmail().isEmpty() || request.getPasswordHash().isEmpty()) {
            throw new RuntimeException("No puedes dejar campos en blanco.");
        }

        LoginDTOResponse response = authRepository.findUserByEmail(request);

        if (response == null) {
            throw new RuntimeException("Correo o contraseña incorrectos.");
        }

        boolean coincide;
        try {
            coincide = BCrypt.checkpw(request.getPasswordHash(), response.getPasswordHash());
        } catch (IllegalArgumentException | StringIndexOutOfBoundsException e) {

            throw new RuntimeException("La contraseña de este usuario no es válida.");
        }

        if (!coincide) {
            throw new RuntimeException("Correo o contraseña incorrectos.");
        }

        return response;
    }
}
