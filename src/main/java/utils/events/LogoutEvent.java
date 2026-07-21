package utils.events;

import javafx.event.Event;
import javafx.event.EventType;

public class LogoutEvent extends Event {

    public static final EventType<LogoutEvent> LOGOUT =
        new EventType<>(Event.ANY, "LOGOUT");

    public LogoutEvent(){
        super(LOGOUT);
    }
}
