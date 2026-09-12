package utils.events.TaskGroup;

import javafx.event.EventType;

public class DelTGEvent extends TGevent{
    
    public static final EventType<DelTGEvent> DEL_TG = 
        new EventType<>(TGevent.ANY, "DEL_TG");
    
    public DelTGEvent(){
        super(DEL_TG);
    }
}
