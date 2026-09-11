package utils.eventhandlers.Task;

import models.Task;
import models.TaskGroup;

import utils.events.Task.UpdateTaskEvent;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;

public class UpdateTaskHandler implements EventHandler<UpdateTaskEvent>{
    private final ObjectProperty<TaskGroup> activeTGProperty;

    public UpdateTaskHandler(ObjectProperty<TaskGroup> activeTGProperty){
        this.activeTGProperty = activeTGProperty;
    }
    
    @Override
    public void handle(UpdateTaskEvent event){
        Task task = event.getTask();
        task.updateTitle(event.getTitle());
        task.updateDescription(event.getDescription());
        task.updateDeadline(event.getDateTime());

        FXCollections.sort(this.activeTGProperty.getValue().getTasksProperty());
    }
}
