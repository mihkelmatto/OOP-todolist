package utils.events.AuthEvent;

import javafx.event.EventType;

public class LogoutEvent extends AuthEvent {

    public static final EventType<LogoutEvent> LOGOUT =
        new EventType<>(AuthEvent.ANY, "LOGOUT");

    public LogoutEvent(){
        super(LOGOUT);
    }
}
