package utils.eventhandlers;

import models.Session;
import models.User;

import UI.SceneManager;
import utils.Auth;
import utils.Classreader;
import utils.events.LoginEvent;

import javafx.event.EventHandler;



public class LoginHandler implements EventHandler<LoginEvent>{

    private SceneManager scenemanager;

    public LoginHandler(SceneManager scenemanager){
        this.scenemanager = scenemanager;
    }

    @Override
    public void handle(LoginEvent event){
        // testkasutaja
        if(event.getUsername().equals("")){
            scenemanager.setSession(new Session(Classreader.findUser("test")));
            scenemanager.showHome();
            return;
        }
        
        User user = Auth.userauth(
            event.getUsername(),
            event.getPassword()
        );

        if(user != null){
            scenemanager.setSession(new Session(user));
            scenemanager.showHome();
        } 
        else{
            System.out.println("Scenemanager: login failed");
        }
    }
    
}
