package UI.Account;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

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
        this.home = new Button("Kodu");
        this.logout = new Button("Logi välja");
        
        
        this.layout.getChildren().addAll(title, home, logout);
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
        layout.getStyleClass().add("Header");
    }

    public HBox getLayout(){
        return this.layout;
    }
}
