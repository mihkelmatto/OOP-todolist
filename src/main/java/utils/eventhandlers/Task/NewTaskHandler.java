package utils.eventhandlers.Task;

import models.Task;
import models.TaskGroup;
import utils.events.Task.NewTaskEvent;

import javafx.beans.property.ObjectProperty;
import javafx.event.EventHandler;

public class NewTaskHandler implements EventHandler<NewTaskEvent>{
    private ObjectProperty<TaskGroup> activeTGProperty;

    public NewTaskHandler(ObjectProperty<TaskGroup> activeTGProperty){
        this.activeTGProperty = activeTGProperty;
    }

    @Override
    public void handle(NewTaskEvent event){
        Task task = new Task();
        this.activeTGProperty.getValue().addTask(task);
    }
}
