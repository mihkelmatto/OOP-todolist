package utils.events.TaskGroup;

import javafx.event.EventType;

public class EditTGEvent extends TGevent{
    
    public static final EventType<EditTGEvent> EDIT_TG = 
        new EventType<>(TGevent.ANY, "EDIT_TG");
    
    public EditTGEvent(){
        super(EDIT_TG);
    }
}
