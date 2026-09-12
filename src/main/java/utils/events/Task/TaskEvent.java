package utils.events.Task;

import javafx.event.Event;
import javafx.event.EventType;

public class TaskEvent extends Event{
    public static final EventType<TaskEvent> ANY =
        new EventType<>(Event.ANY, "TASK");

    public TaskEvent(EventType<? extends TaskEvent> eventType){
        super(eventType);
    }
}
