package UI.Home;
import models.Task;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;

public class TaskCard {
    private Task task;
    private HBox layout;
    private SimpleStringProperty timeDL;
    private SimpleStringProperty dateDL;

    public TaskCard(Task task){
        this.task = task;

        this.timeDL = new SimpleStringProperty();
        this.task.getTimeDLProperty().addListener((obs, oldVal, newVal) -> {
            this.timeDL.set(this.task.formattedTimeDL());
        });
        this.timeDL.set(this.task.formattedTimeDL());        

        this.dateDL = new SimpleStringProperty();
        this.task.getDateDLProperty().addListener((obs, oldVal, newVal) -> {
            this.dateDL.set(this.task.formattedDateDL());
        });
        this.dateDL.set(this.task.formattedDateDL()); 

        this.layout = createLayout();
    }

    private HBox createLayout(){
        HBox layout = new HBox();
        layout.setSpacing(25);


        // Button area
        Button complete = new Button();
        complete.setOnAction(e -> {
            System.out.println("task completed");
            this.task.updateDateTimeDL(LocalDateTime.now());
            System.out.println(this.task.getDeadline());
        });

        complete.getStyleClass().add("Completebutton");
        HBox.setMargin(complete, new Insets(20, 10, 0, 0));


        // Content area
        VBox contentarea = new VBox();

        Label title = new Label(this.task.getTitle());
        title.textProperty().bind(this.task.getTitleProperty());
        title.getStyleClass().add("Taskcard-title");

        Label description = new Label(task.getDescription());
        description.textProperty().bind(this.task.getDescriptionProperty());
        description.setWrapText(true);
        description.getStyleClass().add("Taskcard-description");

        contentarea.getStyleClass().add("Taskcard-contentarea");
        contentarea.getChildren().addAll(title, description);
        
        
        // spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);


        // deadline area
        HBox deadlinewidget = createDeadlinewidget();
        HBox.setMargin(deadlinewidget, new Insets(10, 0, 10, 0));

        // options
        VBox optionsbox = new VBox();
        Button options = new Button("⋮");
        options.setOnAction(e -> {
            System.out.println("options");
        });
        optionsbox.getChildren().add(options);
        optionsbox.getStyleClass().add("Taskcard-optionsbox");

        // main layout
        layout.getChildren().addAll(complete, contentarea, spacer, deadlinewidget, optionsbox);
        layout.getStyleClass().add("TaskCard");
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/TaskCard.css").toExternalForm());
        return layout;
    }

    private HBox createDeadlinewidget(){
        HBox layout = new HBox();
        layout.setSpacing(20);

        Region clockicon = new Region();
        clockicon.getStyleClass().add("clockicon");
        
        VBox deadlinebox = new VBox();     
        deadlinebox.setAlignment(Pos.CENTER_LEFT);

        Label time = new Label();
        time.textProperty().bind(timeDL);
        time.getStyleClass().add("deadline-time");

        Label date = new Label();
        date.getStyleClass().add("deadline-date");
        date.textProperty().bind(dateDL);
        deadlinebox.getChildren().addAll(time, date);

        layout.getStyleClass().add("Taskcard-deadlinewidget");
        layout.getChildren().addAll(clockicon, deadlinebox);
        return layout;
    }

    public HBox getLayout(){
    return this.layout;
   }
}
