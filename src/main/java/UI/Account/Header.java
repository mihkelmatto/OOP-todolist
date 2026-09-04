package UI.Account;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import utils.events.ChangeSceneEvent;
import utils.events.LogoutEvent;
import utils.events.SceneType;

public class Header extends utils.widgets.Header{

    private Button home;
    private Button logout;

    public Header(){
        super(new SimpleStringProperty("Konto"));

        this.home = createHomebutton();
        this.logout = createLogoutbutton();
        
        this.getChildren().addAll(home, logout);
    }

    private Button createHomebutton(){
        Button home = new Button("Kodu");
        home.setOnAction(e -> {
            ChangeSceneEvent showhome = new ChangeSceneEvent(SceneType.HOME);
            home.fireEvent(showhome);
        });
        return home;
    }

    private Button createLogoutbutton(){
        Button logout = new Button("Logi välja");
        logout.setOnAction(e -> {
            LogoutEvent logoutevent = new LogoutEvent();
            logout.fireEvent(logoutevent);
        });
        return logout;
    }
}
