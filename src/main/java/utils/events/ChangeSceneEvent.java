package utils.events;

import javafx.event.Event;
import javafx.event.EventType;

public class ChangeSceneEvent extends Event{

    public static final EventType<ChangeSceneEvent> CHANGE_SCENE =
        new EventType<>(Event.ANY, "CHANGE_SCENE");

    private SceneType scenetype;

    public ChangeSceneEvent(SceneType scenetype){
        super(CHANGE_SCENE);
        this.scenetype = scenetype;
    }

    public SceneType getScenetype(){
        return this.scenetype;
    }
}