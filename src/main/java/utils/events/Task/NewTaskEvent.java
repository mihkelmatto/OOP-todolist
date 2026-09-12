package utils.events.Task;

import javafx.event.EventType;

public class NewTaskEvent extends TaskEvent{
    public static final EventType<NewTaskEvent> NEW_TASK = 
        new EventType<>(TaskEvent.ANY, "NEW_TASK");
    
    public NewTaskEvent(){
        super(NEW_TASK);
    }
}
