package UI;

import UI.Home.HomeScene;
import UI.LoginScene.LoginScene;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Session;
import models.User;
import utils.Auth;

public class SceneManager {
    private Stage stage;
    private Session session;

    public SceneManager(Stage stage){
        this.stage = stage;
    }

    public void showLogin(){
        LoginScene login = new LoginScene();

        // Spooky action at a distance
        login.getScene().addEventFilter(
            ActionEvent.ACTION,
            event -> {

                if (event.getTarget() instanceof Button button) {

                    if(button.getId().equals("loginbutton")){
                        System.out.println("login event");

                        User user = Auth.userauth("Mari");
                        if(user != null){
                            this.session = new Session(user);
                            this.stage.setScene(new HomeScene(this.session).getScene());
                        }
                    }
                    else if(button.getId().equals("registerbutton")){
                        System.out.println("register event");
                    }
                }
            }
        );

        stage.setScene(login.getScene());
    }

    public void showHome(Session session){
        HomeScene home = new HomeScene(session);
        stage.setScene(home.getScene());
    }

    public void stageSettings(){
        Image icon = new Image("icon.png");
        this.stage.getIcons().add(icon);
        this.stage.setTitle("OOP-todolist");

        this.stage.setWidth(1000);
        this.stage.setHeight(750);
        // stage.setResizable(false);

        this.stage.setOnCloseRequest(event -> {
            this.session.save();

            Platform.exit();
            System.exit(0);
        });
    }
}
