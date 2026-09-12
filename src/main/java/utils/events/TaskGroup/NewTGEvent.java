package utils.events.TaskGroup;

import javafx.event.EventType;

public class NewTGEvent extends TGevent{
    
    public static final EventType<NewTGEvent> NEW_TG = 
        new EventType<>(TGevent.ANY, "NEW_TG");
    
    public NewTGEvent(){
        super(NEW_TG);
    }
}
