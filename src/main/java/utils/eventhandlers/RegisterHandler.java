package utils.eventhandlers;

import UI.SceneManager;
import javafx.event.EventHandler;
import models.Session;
import models.User;
import utils.Auth;
import utils.events.RegisterEvent;

public class RegisterHandler implements EventHandler<RegisterEvent>{
    private SceneManager scenemanager;

    public RegisterHandler(SceneManager scenemanager){
        this.scenemanager = scenemanager;
    }

    @Override
    public void handle(RegisterEvent event){
        User user = Auth.createUser(event.getUsername(), event.getPassword());

        if(user != null){
            scenemanager.setSession(new Session(user));
            scenemanager.showHome();
        }
        else{
            System.out.println("Scenemanager: Register failed");
        }
    }


}
