package UI.Account;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import utils.events.ChangeSceneEvent;
import utils.events.LogoutEvent;
import utils.events.SceneType;

public class Header {
    private HBox layout;

    private Button home;
    private Button logout;

    public Header(){
        this.layout = new HBox();
        layout.setSpacing(10);

        Label title = new Label("Konto");
        title.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(title, Priority.ALWAYS);
        this.home = createHomebutton();
        this.logout = createLogoutbutton();
        
        
        this.layout.getChildren().addAll(title, home, logout);
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
        layout.getStyleClass().add("Header");
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

    public HBox getLayout(){
        return this.layout;
    }
}
