package utils;

import javafx.event.Event;
import javafx.event.EventType;

public class RegisterEvent extends Event {

    public static final EventType<RegisterEvent> REGISTER =
        new EventType<>(Event.ANY, "REGISTER");

    private final String username;
    private final String password;

    public RegisterEvent(String username, String password) {
        super(REGISTER);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
