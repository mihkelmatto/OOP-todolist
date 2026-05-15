package UI.Home;
import models.Task;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import java.time.LocalDateTime;

public class TaskCard {
    private Task task;
    private VBox layout;
    private SimpleStringProperty deadline;

    public TaskCard(Task task){
        this.task = task;

        this.deadline = new SimpleStringProperty();
        this.task.getDeadlineProperty().addListener((obs, oldVal, newVal) -> {
            this.deadline.set(this.task.formattedDeadline());
        });
        this.deadline.set(this.task.formattedDeadline());


        this.layout = new VBox();

        Label description = new Label(task.getDescription());
        description.setMaxWidth(Double.MAX_VALUE);
        description.setWrapText(true);
        description.getStyleClass().add("TaskCard-description");

        this.layout.getChildren().addAll(createHeader(), description);
        this.layout.getStyleClass().add("TaskCard");
        this.layout.getStylesheets().add(getClass().getResource("/Stylesheets/TaskCard.css").toExternalForm());
    }
 
    private HBox createHeader(){
        HBox layout = new HBox();
        layout.setSpacing(10);

        // Vasak pool
        Button complete = new Button();
        complete.setOnAction(e -> {
            System.out.println("task completed");
            this.task.updateDeadline(LocalDateTime.now());
            System.out.println(this.task.getDeadline());
        });

        Label title = new Label(this.task.getTitle());

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label deadline = new Label();
        deadline.textProperty().bind(this.deadline);
        
        // Parem pool
        Button options = new Button();
        options.setOnAction(e -> {
            System.out.println("options");
        });

        // stylesheets, layouti lisamine
        layout.getStyleClass().add("TaskCard-header");
        layout.getChildren().addAll(complete, title, spacer, deadline, options);
        return layout;  
    }

    public VBox getLayout(){
    return this.layout;
   }
}
