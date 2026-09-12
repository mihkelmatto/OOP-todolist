package utils.eventhandlers;

import models.Task;
import models.TaskGroup;

import utils.events.Task.DelTaskEvent;
import utils.events.Task.NewTaskEvent;
import utils.events.Task.TaskEvent;
import utils.events.Task.UpdateTaskEvent;

import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;

public class TaskHandler implements EventHandler<TaskEvent>{
    private final ObjectProperty<TaskGroup> activeTGProperty;

    public TaskHandler(ObjectProperty<TaskGroup> activeTGProperty){
        this.activeTGProperty = activeTGProperty;
    }

    @Override
    public void handle(TaskEvent event){
        switch (event) {
            case NewTaskEvent e -> 
                newTask();

            case UpdateTaskEvent e ->
                updateTask(e);

            case DelTaskEvent e -> 
                delTask(e);
            
            default ->
                System.out.println("TaskHandler: invalid event type");
        }
    }

    private void newTask(){
        Task task = new Task();
        this.activeTGProperty.getValue().addTask(task);
    }

    private void updateTask(UpdateTaskEvent event){
        Task task = event.getTask();
        task.updateTitle(event.getTitle());
        task.updateDescription(event.getDescription());
        task.updateDeadline(event.getDateTime());

        FXCollections.sort(this.activeTGProperty.getValue().getTasksProperty());
    }

    private void delTask(DelTaskEvent event){
        TaskGroup activeTG = this.activeTGProperty.getValue();
        activeTG.removeTask(event.getTask());
    }
}
