package UI.Home;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import models.Task;

public class TaskCard {
    private Task task;
    private VBox layout;

    public TaskCard(Task task){
        this.task = task;
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
        
        // Headeri sisu loomine
        Button complete = new Button();
        complete.setOnAction(e -> {
            System.out.println("task completed");
        });

        Button options = new Button();
        options.setOnAction(e -> {
            System.out.println("options");
        });

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Label title = new Label(this.task.getTitle());
        Label taskgroup = new Label("Taskgroup"); // TODO: TaskGroup teistesse klassidesse laiali?
        Label deadline = new Label(this.task.formattedDeadline());

        // stylesheets, layouti lisamine
        layout.getStyleClass().add("TaskCard-header");
        layout.getChildren().addAll(complete, title, spacer, taskgroup, deadline, options);
        return layout;  
    }

    public VBox getLayout(){
    return this.layout;
   }
}
