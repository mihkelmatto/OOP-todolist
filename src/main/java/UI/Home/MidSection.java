package UI.Home;

import java.time.LocalDateTime;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Task;

public class MidSection {
    private ScrollPane layout;

    public MidSection(){
        VBox content = new VBox();
        content.setSpacing(10);

        String description = "description description description description description description description description description description description description description description description description description ";
        for(int i = 0; i<20; i++){
            Task task = new Task("Task " + i, description, LocalDateTime.of(2026, 7, 10, 15, 30));
            TaskCard card = new TaskCard(task);
            content.getChildren().add(card.getLayout());
        }
        
        // content.getStyleClass().add("MidSection");
        this.layout = new ScrollPane(content);
        this.layout.setFitToWidth(true);
        this.layout.getStyleClass().add("MidSection");
    }

    public ScrollPane getLayout(){
        return this.layout;
    }
}
