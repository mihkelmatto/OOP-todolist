package utils.events.TaskGroup;

import javafx.event.Event;
import javafx.event.EventType;

public class EditTGEvent extends Event{
    public static final EventType<EditTGEvent> EDIT_TG = 
        new EventType<>(Event.ANY, "EDIT_TG");
    
    public EditTGEvent(){
        super(EDIT_TG);
    }
}
