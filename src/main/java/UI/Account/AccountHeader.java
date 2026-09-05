package UI.Account;

import utils.events.ChangeSceneEvent;
import utils.events.LogoutEvent;
import utils.events.SceneType;

import javafx.scene.control.Button;

public class AccountHeader extends utils.widgets.Header{

    private Button home;
    private Button logout;

    public AccountHeader(){
        super("Konto");
        this.home = new Button("Kodu");
        this.logout = new Button("Logi välja");

        initLayout();

        // events / listeners
        home.setOnAction(e -> {
            ChangeSceneEvent showhome = new ChangeSceneEvent(SceneType.HOME);
            home.fireEvent(showhome);
        });
        
        logout.setOnAction(e -> {
            LogoutEvent logoutevent = new LogoutEvent();
            logout.fireEvent(logoutevent);
        });
    }
    
    private void initLayout(){
        this.getChildren().addAll(home, logout);
    }
}
