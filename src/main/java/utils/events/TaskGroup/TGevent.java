package utils.events.TaskGroup;

import javafx.event.Event;
import javafx.event.EventType;

public class TGevent extends Event{

    public static final EventType<TGevent> ANY = new EventType<>(Event.ANY, "TG");

    public TGevent(EventType<? extends TGevent> eventType){
        super(eventType);
    }
}
