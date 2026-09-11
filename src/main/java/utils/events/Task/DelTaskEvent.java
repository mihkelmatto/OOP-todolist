package utils.events.Task;

import javafx.event.Event;
import javafx.event.EventType;
import models.Task;

public class DelTaskEvent extends Event{
    public static final EventType<DelTaskEvent> DEL_TASK = 
        new EventType<>(Event.ANY, "DEL_TASK");
        private Task task;

    public DelTaskEvent(Task task){
        super(DEL_TASK);
        this.task = task;
    }

    public Task getTask(){
        return this.task;
    }
}
