package UI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Session;

/**
 * JavaFX App
 */

public class Main extends Application {
    SceneManager scenemanager;

    @Override
    public void start(Stage stage) {
        this.scenemanager = new SceneManager(stage);
        
        stageSettings(stage);
        scenemanager.showLogin();
        stage.show();
    }

    public void stageSettings(Stage stage){
        Image icon = new Image("/images/windowicon.png");
        stage.getIcons().add(icon);
        stage.setTitle("OOP-todolist");

        stage.setWidth(1000);
        stage.setHeight(750);
        // stage.setResizable(false);

        stage.setOnCloseRequest(event -> {
            Session session = this.scenemanager.getSession();

            if(session != null) session.save();

            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}