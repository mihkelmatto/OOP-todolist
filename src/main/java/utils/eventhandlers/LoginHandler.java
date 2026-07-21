package utils.eventhandlers;

import UI.SceneManager;
import javafx.event.EventHandler;
import models.Session;
import models.User;
import utils.Auth;
import utils.events.LoginEvent;

public class LoginHandler implements EventHandler<LoginEvent>{

    private SceneManager scenemanager;

    public LoginHandler(SceneManager scenemanager){
        this.scenemanager = scenemanager;
    }

    @Override
    public void handle(LoginEvent event){
               
        User user = Auth.userauth(
            event.getUsername(),
            event.getPassword()
        );

        if(user != null){
            scenemanager.setSession(new Session(user));
            scenemanager.showHome();;
        } 
        else{
            System.out.println("Scenemanager: login failed");
        }
    }
    
}
