package utils.events.AuthEvent;

import javafx.event.EventType;

public class LoginEvent extends AuthEvent {

    public static final EventType<LoginEvent> LOGIN =
        new EventType<>(AuthEvent.ANY, "LOGIN");

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
