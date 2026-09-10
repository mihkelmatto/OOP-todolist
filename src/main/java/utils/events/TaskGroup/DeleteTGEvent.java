package utils.events.TaskGroup;

import javafx.event.Event;
import javafx.event.EventType;

public class DeleteTGEvent extends Event{
    public static final EventType<DeleteTGEvent> DELETE_TG = 
        new EventType<>(Event.ANY, "DELETE_TG");
    
    public DeleteTGEvent(){
        super(DELETE_TG);
    }
}
