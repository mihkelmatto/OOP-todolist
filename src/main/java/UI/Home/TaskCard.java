package UI.Home;
import models.Session;
import models.Task;
import models.TaskGroup;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TaskCard {
    private Session session;
    private Task task;
    private HBox layout;
    private Button options;
    private SimpleStringProperty timeDL;
    private SimpleStringProperty dateDL;

    public TaskCard(Task task, Session session){
        this.task = task;
        this.session = session;

        this.timeDL = new SimpleStringProperty();
        this.task.getDeadlineProperty().addListener((obs, oldVal, newVal) -> {
            updateDeadlineProperties(newVal);
        });

        this.dateDL = new SimpleStringProperty();
        this.task.getDeadlineProperty().addListener((obs, oldVal, newVal) -> {
            updateDeadlineProperties(newVal);
        });

        updateDeadlineProperties(this.task.getDeadlineProperty().getValue());
        this.layout = createLayout();
    }

    private void updateDeadlineProperties(LocalDateTime deadline){
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
        this.timeDL.set(deadline.toLocalTime().format(timeformat));

        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("d.MMM yyyy", Locale.getDefault());
        this.dateDL.set(deadline.toLocalDate().format(dateformat));
    }

    private HBox createLayout(){
        HBox layout = new HBox();
        layout.setSpacing(25);


        // Button area
        Button complete = new Button();
        complete.setOnAction(e -> {
            TaskGroup activeTG = this.session.getActiveTGProperty().getValue();
            activeTG.removeTask(this.task);
        });

        complete.setId("Completebutton");
        HBox.setMargin(complete, new Insets(20, 10, 0, 0));

        // Content area
        VBox contentarea = new VBox();
        
        TextField title = new TextField();
        title.setEditable(false);
        title.setText(this.task.getTitleProperty().getValue());
        title.setId("Taskcard-title");

        TextField description = new TextField();
        description.setEditable(false);
        description.setText(this.task.getDescriptionProperty().getValue());
        description.setId("Taskcard-description");   

        contentarea.getStyleClass().add("Taskcard-contentarea");
        contentarea.getChildren().addAll(title, description);
        HBox.setHgrow(contentarea, Priority.ALWAYS);

        // options
        VBox optionsbox = new VBox();
        this.options = new Button(); // ⋮
        this.options.addEventHandler(javafx.event.ActionEvent.ACTION, e -> {
            if(title.isEditable()){
                title.setEditable(false);
                this.task.updateTitle(title.getText());
                title.getStyleClass().remove("Taskcard-editable");

                description.setEditable(false);
                this.task.updateDescription(description.getText());
                description.getStyleClass().remove("Taskcard-editable");

            }
            else{
                title.setEditable(true);
                title.getStyleClass().add("Taskcard-editable");

                description.setEditable(true);
                description.getStyleClass().add("Taskcard-editable");
            }
        });

        optionsbox.getChildren().add(options);
        optionsbox.getStyleClass().add("Taskcard-optionsbox");

        // deadline area
        HBox deadlinewidget = createDeadlinewidget();
        HBox.setMargin(deadlinewidget, new Insets(10, 0, 10, 0));

        // main layout
        layout.getChildren().addAll(complete, contentarea, deadlinewidget, optionsbox);
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
        
        TextField time = new TextField();
        time.setEditable(false);
        time.setFocusTraversable(false);
        time.setText(this.timeDL.getValue());
        time.getStyleClass().add("deadline");
        time.setId("deadline-time");

        TextField date = new TextField();
        date.setEditable(false);
        date.setFocusTraversable(false);
        date.setText(this.dateDL.getValue());
        date.getStyleClass().add("deadline");
        date.setId("deadline-date");

        this.options.addEventHandler(javafx.event.ActionEvent.ACTION, e -> {
            if(time.isEditable()){
                time.setEditable(false);
                time.setFocusTraversable(false);
                time.getStyleClass().remove("Taskcard-editable");
                
                date.setEditable(false);
                date.setFocusTraversable(false);
                date.getStyleClass().remove("Taskcard-editable");
                
                TaskGroup activeTG = this.session.getActiveTGProperty().getValue();

                this.task.updateDeadline(time.getText(), date.getText());
                FXCollections.sort(activeTG.getTasksProperty());
            }
            else{
                time.setEditable(true);
                time.setFocusTraversable(true);
                time.clear();
                time.setPromptText("HH.mm");
                time.getStyleClass().add("Taskcard-editable");

                date.setEditable(true);
                date.setFocusTraversable(true);
                date.clear();
                date.setPromptText("dd.MM.yyyy");
                date.getStyleClass().add("Taskcard-editable");
            }
        });

        deadlinebox.getChildren().addAll(time, date);

        layout.setId("Taskcard-deadlinewidget");
        layout.getChildren().addAll(clockicon, deadlinebox);
        return layout;
    }

    public HBox getLayout(){
    return this.layout;
   }
}
