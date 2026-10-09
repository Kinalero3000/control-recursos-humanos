package main.java.com.jgunzalesindustries.control.recursos.humanos.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request.LoginDTORequest;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response.LoginDTOResponse;
import main.java.com.jgunzalesindustries.control.recursos.humanos.service.AuthService;
import main.java.com.jgunzalesindustries.control.recursos.humanos.util.SceneManager;

public class LoginController {


    private final AuthService authService;
    private final SceneManager sceneManager;

    
    @FXML private TextField txtFieldEmail;
    @FXML private PasswordField txtFieldPassword;


    public LoginController(AuthService authService, SceneManager sceneManager) {
        this.authService = authService;
        this.sceneManager = sceneManager;
    }

    @FXML
    private void handleLogin() throws Exception {

     
        String email = txtFieldEmail.getText() == null ? "" : txtFieldEmail.getText().trim();
        String password = txtFieldPassword.getText() == null ? "" : txtFieldPassword.getText();

    
        if (email.isEmpty() || password.isEmpty()) {
            sceneManager.showAlert("Campos vacíos", "No puedes dejar espacios en blanco",
                    "Intenta de nuevo.", Alert.AlertType.INFORMATION);
            return; 
        }

     
        LoginDTOResponse response;
        try {
            response = authService.login(new LoginDTORequest(email, password));
        } catch (RuntimeException e) {
            sceneManager.showAlert("Error al iniciar sesión", "Verifica tus datos",
                    e.getMessage(), Alert.AlertType.WARNING);
            return;
        }

        sceneManager.showAlert("Bienvenido " + response.getNames(), "Es bueno verte",
                "Rol: " + response.getRolName(), Alert.AlertType.INFORMATION);
        sceneManager.showClientView();
    }

    @FXML
    private void handleGoToRegister() throws Exception {
        sceneManager.showRegisterView();
    }
}


