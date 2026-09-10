package UI.Account;

import utils.events.AuthEvent.LogoutEvent;
import utils.events.SceneEvent.ChangeSceneEvent;
import utils.events.SceneEvent.SceneType;
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
        home.setOnAction(e -> home.fireEvent(new ChangeSceneEvent(SceneType.HOME)));
        logout.setOnAction(e -> logout.fireEvent(new LogoutEvent()));
    }
    
    private void initLayout(){
        this.getChildren().addAll(home, logout);
    }
}
