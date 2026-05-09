package UI.Home;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MidSection {
    private ScrollPane layout;

    public MidSection(){
        VBox content = new VBox();
        content.setSpacing(10);

        for(int i = 0; i<20; i++){
            content.getChildren().add(new Label("Row " + i));
        }
        
        // content.getStyleClass().add("MidSection");
        this.layout = new ScrollPane(content);
        this.layout.getStyleClass().add("MidSection");
    }

    public ScrollPane getLayout(){
        return this.layout;
    }
}
