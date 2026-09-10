package utils.events.TaskGroup;

import javafx.event.Event;
import javafx.event.EventType;

public class NewTGEvent extends Event{
    public static final EventType<NewTGEvent> NEW_TG = 
        new EventType<>(Event.ANY, "NEW_TG");
    
    public NewTGEvent(){
        super(NEW_TG);
    }
}
