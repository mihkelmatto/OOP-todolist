package utils.eventhandlers;

import UI.SceneManager;
import utils.events.LogoutEvent;

import javafx.event.EventHandler;

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