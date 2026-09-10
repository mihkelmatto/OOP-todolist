package utils.events.AuthEvent;

import javafx.event.Event;
import javafx.event.EventType;

public class AuthEvent extends Event{
    public static final EventType<AuthEvent> ANY = new EventType<>(Event.ANY, "AUTH");

    public AuthEvent(EventType<? extends AuthEvent> eventType){
        super(eventType);
    }
}
