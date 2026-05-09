package UI;

import UI.Home.HomeScene;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * JavaFX App
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        HomeScene home = new HomeScene();

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

    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}