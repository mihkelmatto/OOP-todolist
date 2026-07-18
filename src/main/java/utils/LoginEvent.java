package utils;

import javafx.event.Event;
import javafx.event.EventType;

public class LoginEvent extends Event {

    public static final EventType<LoginEvent> LOGIN =
        new EventType<>(Event.ANY, "LOGIN");

    private final String username;
    private final String password;

    public LoginEvent(String username, String password) {
        super(LOGIN);
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
