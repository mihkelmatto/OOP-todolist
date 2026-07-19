package UI;

import UI.Account.AccountScene;
import UI.Home.HomeScene;
import UI.Login.LoginScene;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Session;
import models.User;
import utils.Auth;
import utils.LoginEvent;
import utils.RegisterEvent;

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
            LoginEvent.LOGIN,
            event -> {
                String username = event.getUsername();
                String password = event.getPassword();

                System.out.printf("Login input: %s, Password input: %s \n", username, password);
                
                User user = Auth.userauth(username, password);
                if(user != null){
                    this.session = new Session(user);
                    this.stage.setScene(new AccountScene(this.session.getUser()).getScene()); // TODO: teha nupp, hetkel homescene asendatud
                } else System.out.println("Scenemanager: login failed");
            }
        );

        login.getScene().addEventFilter(
            RegisterEvent.REGISTER,
            event -> {
                User user = Auth.createUser(event.getUsername(), event.getPassword());
                if(user != null){
                    this.session = new Session(user);
                    this.stage.setScene(new HomeScene(session).getScene());
                } else System.out.println("Scenemanager: Register failed");
            }
        );

        stage.setScene(login.getScene());
    }

    public void showHome(Session session){
        HomeScene home = new HomeScene(session);
        stage.setScene(home.getScene());
    }

    public void showAccount(){
        AccountScene account = new AccountScene(session.getUser());
        stage.setScene(account.getScene());
    }

    public void stageSettings(){
        Image icon = new Image("/images/windowicon.png");
        this.stage.getIcons().add(icon);
        this.stage.setTitle("OOP-todolist");

        this.stage.setWidth(1000);
        this.stage.setHeight(750);
        // stage.setResizable(false);

        this.stage.setOnCloseRequest(event -> {
            if(this.session != null) this.session.save();

            Platform.exit();
            System.exit(0);
        });
    }
}
