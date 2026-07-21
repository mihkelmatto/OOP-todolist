package utils.eventhandlers;

import UI.SceneManager;
import javafx.event.EventHandler;
import utils.events.LogoutEvent;

public class LogoutEventHandler implements EventHandler<LogoutEvent>{

    private SceneManager scenemanager;

    public LogoutEventHandler(SceneManager scenemanager){
        this.scenemanager = scenemanager;
    }

    @Override
    public void handle(LogoutEvent event){
        this.scenemanager.getSession().save();
        this.scenemanager.showLogin();
    }
    
}