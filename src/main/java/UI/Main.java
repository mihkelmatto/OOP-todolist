package UI;

import javafx.application.Application;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class Main extends Application {
    SceneManager scenemanager;

    @Override
    public void start(Stage stage) {
        this.scenemanager = new SceneManager(stage);
        
        scenemanager.stageSettings();
        scenemanager.showLogin();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}