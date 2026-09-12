package utils.events.Task;

import models.Task;
import javafx.event.EventType;

public class DelTaskEvent extends TaskEvent{
    public static final EventType<DelTaskEvent> DEL_TASK = 
        new EventType<>(TaskEvent.ANY, "DEL_TASK");

    private Task task;

    public DelTaskEvent(Task task){
        super(DEL_TASK);
        this.task = task;
    }

    public Task getTask(){
        return this.task;
    }
}
