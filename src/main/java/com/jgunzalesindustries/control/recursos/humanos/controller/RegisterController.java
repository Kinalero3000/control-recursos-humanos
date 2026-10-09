package main.java.com.jgunzalesindustries.control.recursos.humanos.controller;


import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.request.RegisterDTORequest;
import main.java.com.jgunzalesindustries.control.recursos.humanos.dto.response.RolResponse;
import main.java.com.jgunzalesindustries.control.recursos.humanos.repository.UserRepository;
import main.java.com.jgunzalesindustries.control.recursos.humanos.service.RegisterService;
import main.java.com.jgunzalesindustries.control.recursos.humanos.util.SceneManager;


public class RegisterController implements Initializable {


    private final RegisterService registerService;
    private final SceneManager sceneManager;
    private final UserRepository userRepository; 

    @FXML private TextField txtFieldName;
    @FXML private TextField txtFieldLastName;
    @FXML private TextField txtFieldEmail;
    @FXML private PasswordField txtFieldPassword;
    @FXML private PasswordField txtFieldConfirmPassword;
    @FXML private ComboBox<RolResponse> comboBoxRol;


    public RegisterController(RegisterService registerService, SceneManager sceneManager,
                              UserRepository userRepository) {
        this.registerService = registerService;
        this.sceneManager = sceneManager;
        this.userRepository = userRepository;
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxRol.setItems(userRepository.findAllRoles());
    }

    @FXML
    private void handleRegister() {
        try {

            RegisterDTORequest request = new RegisterDTORequest(
                    txtFieldName.getText(),
                    txtFieldLastName.getText(),
                    txtFieldEmail.getText(),
                    txtFieldPassword.getText(),
                    txtFieldConfirmPassword.getText(),
                    comboBoxRol.getValue().getIdRol());

            registerService.register(request);

            sceneManager.showAlert("Registro exitoso", "Cuenta creada",
                    "Ahora puedes iniciar sesión.", AlertType.INFORMATION);
            sceneManager.showLoginView(); // volver al login

        } catch (IllegalArgumentException e) {
            sceneManager.showAlert("Datos inválidos", "Verifica los campos",
                    e.getMessage(), AlertType.WARNING);
        } catch (Exception e) {
            sceneManager.showAlert("Error", "Error al registrar",
                    "No se pudo completar el registro.", AlertType.ERROR);
        }
    }

    @FXML
    private void handleGoToLogin() throws Exception {
        sceneManager.showLoginView();
    }
}


