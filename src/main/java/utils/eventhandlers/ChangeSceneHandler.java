package utils.eventhandlers;

import UI.SceneManager;
import utils.events.ChangeSceneEvent;

import javafx.event.EventHandler;

public class ChangeSceneHandler implements EventHandler<ChangeSceneEvent>{

    private SceneManager scenemanager;

    public ChangeSceneHandler(SceneManager scenemanager){
        this.scenemanager = scenemanager;
    }

    @Override
    public void handle(ChangeSceneEvent event){
        switch(event.getScenetype()) {
            case LOGIN -> scenemanager.showLogin();
            case HOME -> scenemanager.showHome();
            case ACCOUNT -> scenemanager.showAccount();
        }
    }    
}