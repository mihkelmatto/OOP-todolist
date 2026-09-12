package utils.events.Task;

import models.Task;

import java.time.LocalDateTime;
import javafx.event.EventType;


public class UpdateTaskEvent extends TaskEvent{
    public static final EventType<UpdateTaskEvent> UPDATE_TASK = 
        new EventType<>(TaskEvent.ANY, "UPDATE_TASK");

    private Task task;
    private String title;
    private String description;
    private LocalDateTime datetime;

    public UpdateTaskEvent(Task task, String title, String description, LocalDateTime datetime){
        super(UPDATE_TASK);
        this.task = task;
        this.title = title;
        this.description = description;
        this.datetime = datetime;
    }

    public Task getTask(){
        return this.task;
    }

    public String getTitle(){
        return this.title;
    }

    public String getDescription(){
        return this.description;
    }

    public LocalDateTime getDateTime(){
        return this.datetime;
    }
}
