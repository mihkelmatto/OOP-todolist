package UI.Home;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Header {
    private VBox layout;

    public Header(){
        VBox layout = new VBox();
        
        // layout.getChildren().add(new Label("Title"));
        layout.getChildren().add(getNavbar());
        layout.getStyleClass().add("Header");

        this.layout = layout;
    }

    HBox getNavbar(){
        HBox navbar = new HBox();
        navbar.setSpacing(10);

        for(int i = 1; i<5; i++){
            String category = String.format("Category %s", i);
            navbar.getChildren().add(new Button(category));
        }
        navbar.getStyleClass().add("Navbar");
        return navbar;
    }

    public VBox getLayout(){
        return this.layout;
    }
}
