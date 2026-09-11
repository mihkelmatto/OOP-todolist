package utils.eventhandlers.Task;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;
import models.TaskGroup;
import utils.events.Task.DelTaskEvent;

public class DelTaskHandler implements EventHandler<DelTaskEvent>{
    private ObjectProperty<TaskGroup> activeTGProperty;

    public DelTaskHandler(ObjectProperty<TaskGroup> activeTGProperty){
        this.activeTGProperty = activeTGProperty;
    }
    
    @Override
    public void handle(DelTaskEvent event){
        TaskGroup activeTG = this.activeTGProperty.getValue();
        activeTG.removeTask(event.getTask());
    }
}
