package UI.Home;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class Header {
    private VBox layout;

    public Header(){
        VBox layout = new VBox();
        
        layout.getChildren().add(new Label("Title"));
        layout.getChildren().add(new Label("Navbar: Categories"));
        layout.getStyleClass().add("Header");

        this.layout = layout;
    }

    public VBox getLayout(){
        return this.layout;
    }
}
