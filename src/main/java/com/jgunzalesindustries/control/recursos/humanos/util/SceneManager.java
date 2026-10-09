package main.java.com.jgunzalesindustries.control.recursos.humanos.util;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import main.java.com.jgunzalesindustries.control.recursos.humanos.controller.LoginController;
import main.java.com.jgunzalesindustries.control.recursos.humanos.repository.AuthRepository;
import main.java.com.jgunzalesindustries.control.recursos.humanos.repository.UserRepository;
import main.java.com.jgunzalesindustries.control.recursos.humanos.service.AuthService;
import main.java.com.jgunzalesindustries.control.recursos.humanos.service.RegisterService;


public class SceneManager {


    private static final String VIEW_PATH = "/view/";
    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

 

    public void showLoginView() throws IOException {
        AuthService authService = new AuthService(new AuthRepository());
       
        LoginController controller = new LoginController(authService, this);
        loadScene("login-view.fxml", controller, "Iniciar sesión");
    }

    public void showRegisterView() throws IOException {
        UserRepository userRepository = new UserRepository();
        RegisterService registerService = new RegisterService(userRepository);
        RegisterController controller = new RegisterController(registerService, this, userRepository);
        loadScene("register-view.fxml", controller, "Registro");
    }

    public void showClientView() throws IOException {
        ClientController controller = new ClientController(this);
        loadScene("client-view.fxml", controller, "Clientes");
    }

    private void loadScene(String fxmlFile, Object controller, String title) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(VIEW_PATH + fxmlFile));
        loader.setController(controller);  
        Parent root = loader.load();       

        stage.setTitle(title);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void showAlert(String title, String header, String content, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
