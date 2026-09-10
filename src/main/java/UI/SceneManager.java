package UI;

import models.Session;

import UI.Account.AccountScene;
import UI.Home.HomeScene;
import UI.Login.LoginScene;
import utils.events.AuthEvent.LoginEvent;
import utils.events.AuthEvent.LogoutEvent;
import utils.events.AuthEvent.RegisterEvent;
import utils.events.SceneEvent.ChangeSceneEvent;
import utils.eventhandlers.AuthHandler;
import utils.eventhandlers.ChangeSceneHandler;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneManager {
    private final Stage stage;
    private final AuthHandler authHandler;
    private final ChangeSceneHandler changeSceneHandler;
    private Session session;

    public SceneManager(Stage stage){
        this.stage = stage;
        this.authHandler = new AuthHandler(this);
        this.changeSceneHandler = new ChangeSceneHandler(this);
    }

    public void showLogin(){
        Scene loginscene = new LoginScene();

        loginscene.addEventHandler(LoginEvent.LOGIN, this.authHandler);
        loginscene.addEventHandler(RegisterEvent.REGISTER, this.authHandler);

        stage.setScene(loginscene);
    }
    
    public void showHome(){
        Scene homescene = new HomeScene(this.session);

        homescene.addEventHandler(ChangeSceneEvent.CHANGE_SCENE, this.changeSceneHandler);

        stage.setScene(homescene);
    }
    
    public void showAccount(){
        Scene accountscene = new AccountScene(this.session.getUser());

        accountscene.addEventHandler(ChangeSceneEvent.CHANGE_SCENE, this.changeSceneHandler);
        accountscene.addEventHandler(LogoutEvent.LOGOUT, this.authHandler);

        stage.setScene(accountscene);
    }

    public Session getSession(){
        return this.session;
    }

    public void setSession(Session session){
        this.session = session;
    }
}
