package UI.Home;

import models.Session;
import models.Task;
import models.TaskGroup;
import utils.UIUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class DLwidget {
    private HBox layout;
    
    private Session session;
    private Task task;

    private TextField time;
    private TextField date;
    private SimpleStringProperty timeDL;
    private SimpleStringProperty dateDL;

    DLwidget(Session session, Task task){
        this.session = session;
        this.task = task;

        this.timeDL = new SimpleStringProperty();
        this.task.getDeadlineProperty().addListener((obs, oldVal, newVal) -> {
            updateDeadlineProperties(newVal);
        });

        this.dateDL = new SimpleStringProperty();
        this.task.getDeadlineProperty().addListener((obs, oldVal, newVal) -> {
            updateDeadlineProperties(newVal);
        });

        updateDeadlineProperties(task.getDeadlineProperty().getValue());
        this.layout = createlayout();
    }

    private HBox createlayout(){
        HBox layout = new HBox();
        layout.setSpacing(20);

        Region clockicon = new Region();
        clockicon.getStyleClass().add("clockicon");
        
        // deadline box
        VBox timebox = new VBox();     
        timebox.setAlignment(Pos.CENTER_LEFT);
        
        this.time = UIUtils.createTextfield(this.timeDL.getValue(), "deadline-time");
        time.getStyleClass().add("deadline");

        this.date = UIUtils.createTextfield(this.dateDL.getValue(), "deadline-date");
        date.getStyleClass().add("deadline");
        
        timebox.getChildren().addAll(time, date);

        layout.setId("deadlinewidget");
        layout.getChildren().addAll(clockicon, timebox);
        return layout;
    }

    public void toggleEditable(){
        if(time.isEditable()){
            TaskGroup activeTG = this.session.getActiveTGProperty().getValue();
            
            this.task.updateDeadline(time.getText(), date.getText());
            FXCollections.sort(activeTG.getTasksProperty());
        }
        else{
            time.clear();
            time.setPromptText("HH.mm");

            date.clear();
            date.setPromptText("dd.MM.yyyy");
        }

        UIUtils.toggleEditable(this.time, this.date);
    }

    private void updateDeadlineProperties(LocalDateTime deadline){
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
        this.timeDL.set(deadline.toLocalTime().format(timeformat));

        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("d.MMM yyyy", Locale.getDefault());
        this.dateDL.set(deadline.toLocalDate().format(dateformat));
    }

    public HBox getLayout(){
        return this.layout;
    }

}

