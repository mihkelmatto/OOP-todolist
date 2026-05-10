package UI.Home;

import java.time.LocalDateTime;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Task;

public class TaskCard {
    private Task task;
    private VBox layout;

    public TaskCard(Task task){
        this.task = task;
        this.layout = new VBox();
        
        Label title = new Label(task.getTitle());
        Label deadline = new Label(task.getFormattedDeadline());
        Label description = new Label(task.getDescription());

        for(Label l : new Label[]{title, deadline, description}){
            l.setMaxWidth(Double.MAX_VALUE);
            // l.prefWidthProperty().bind(layout.widthProperty());
            l.setWrapText(true);
        }

        description.getStyleClass().add("TaskCard-description");

        this.layout.getChildren().addAll(title, deadline, description);
        this.layout.getStyleClass().add("TaskCard");
    }

   public VBox getLayout(){
    return this.layout;
   }
}
