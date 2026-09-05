package UI;

import models.Session;

import UI.Account.AccountScene;
import UI.Home.HomeScene;
import UI.Login.LoginScene;

import utils.eventhandlers.ChangeSceneHandler;
import utils.eventhandlers.LoginHandler;
import utils.eventhandlers.LogoutEventHandler;
import utils.eventhandlers.RegisterHandler;
import utils.events.ChangeSceneEvent;
import utils.events.LoginEvent;
import utils.events.LogoutEvent;
import utils.events.RegisterEvent;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class SceneManager {
    private Stage stage;
    private Session session;

    public SceneManager(Stage stage){
        this.stage = stage;
    }

    public void showLogin(){
        Scene loginscene = new LoginScene();

        loginscene.addEventHandler(
            LoginEvent.LOGIN, 
            new LoginHandler(this)
        );

        loginscene.addEventHandler(
            RegisterEvent.REGISTER,
            new RegisterHandler(this)    
        );

        stage.setScene(loginscene);
    }
    
    public void showHome(){
        Scene homescene = new HomeScene(this.session);
        addChangeSceneHandlers(homescene);
        stage.setScene(homescene);
    }
    
    public void showAccount(){
        Scene accountscene = new AccountScene(this.session.getUser());
        addChangeSceneHandlers(accountscene);
        accountscene.addEventHandler(
            LogoutEvent.LOGOUT,
            new LogoutEventHandler(this)
        );
        stage.setScene(accountscene);
    }

    public void addChangeSceneHandlers(Scene scene){
        scene.addEventHandler(
            ChangeSceneEvent.CHANGE_SCENE,
            new ChangeSceneHandler(this)
        );
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

    public Session getSession(){
        return this.session;
    }

    public void setSession(Session session){
        this.session = session;
    }
}
