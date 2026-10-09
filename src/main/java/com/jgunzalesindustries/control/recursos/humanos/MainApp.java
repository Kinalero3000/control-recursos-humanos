package main.java.com.jgunzalesindustries.control.recursos.humanos;

import javafx.application.Application;
import javafx.stage.Stage;
import main.java.com.jgunzalesindustries.control.recursos.humanos.util.SceneManager;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SceneManager sceneManager = new SceneManager(primaryStage);
        sceneManager.showLoginView(); // la primera pantalla es el login
    }

    public static void main(String[] args) {
        launch(args); // arranca JavaFX y termina llamando a start()
    }
}
    

