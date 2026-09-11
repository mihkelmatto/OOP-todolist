package utils.events.Task;

import javafx.event.Event;
import javafx.event.EventType;

public class NewTaskEvent extends Event{
    public static final EventType<NewTaskEvent> NEW_TASK = 
        new EventType<>(Event.ANY, "NEW_TASK");
    
    public NewTaskEvent(){
        super(NEW_TASK);
    }
}
