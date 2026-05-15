package UI;

import UI.Home.HomeScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Session;
/**
 * JavaFX App
 */
public class Main extends Application {
    Session session;

    @Override
    public void start(Stage stage) {
        this.session = new Session("Mari");
        HomeScene home = new HomeScene(this.session);

        stageSettings(stage);

        stage.setScene(home.getScene());
        stage.show();
    }

    public void stageSettings(Stage stage){
        Image icon = new Image("icon.png");
        stage.getIcons().add(icon);
        stage.setTitle("OOP-todolist");

        stage.setWidth(1000);
        stage.setHeight(750);
        // stage.setResizable(false);

        stage.setOnCloseRequest(event -> {
            this.session.save();

            Platform.exit();
            System.exit(0);
        });
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}