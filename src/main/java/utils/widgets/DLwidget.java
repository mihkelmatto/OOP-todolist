package utils.widgets;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import models.Session;
import models.Task;
import models.TaskGroup;

public class DLwidget extends HBox{
    private Session session;
    private Task task;
    
    private EditableField time;
    private EditableField date;

    public DLwidget(Session session, Task task){
        // field init
        this.session = session;
        this.task = task;

        // layout
        this.setSpacing(20);
        Region clockicon = new Region();
        clockicon.getStyleClass().add("clockicon");
        
        VBox timebox = createTimebox();
        
        // css
        this.getChildren().addAll(clockicon, timebox);
        this.setId("deadlinewidget");
        
        // events
        this.task.getDeadlineProperty().addListener(e -> {
            updateDeadlineProperties();
        });
        updateDeadlineProperties();
    }

    private VBox createTimebox(){
        VBox timebox = new VBox();     
        timebox.setAlignment(Pos.CENTER_LEFT);
        
        this.time = new EditableField("", "deadline-time");
        this.time.getValueField().setPromptText("HH.mm");
        time.getStyleClass().add("deadline");

        this.date = new EditableField("", "deadline-date");
        this.date.getValueField().setPromptText("dd.MM.yyyy");
        date.getStyleClass().add("deadline");
        
        timebox.getChildren().addAll(time, date);

        return timebox;
    }

    // TODO: update toimub millegipärast 2x? /// UpdateDeadline() uuendab deadline ennast, mille peale this.task.listener kutsub updateDeadline()
    // nupp ei editi peale esimest korda
    public void toggleEditable(){
        if(this.time.isEditable() || this.date.isEditable()){
            TaskGroup activeTG = this.session.getActiveTGProperty().getValue();
            
            this.task.updateDeadline(this.time.getValue(), this.date.getValue());
            FXCollections.sort(activeTG.getTasksProperty());

            this.time.setEditable(false);
            this.date.setEditable(false);
        }
        else{
            this.time.setEditable(true);
            this.date.setEditable(true);     
        }
    }

    private void updateDeadlineProperties(){
        LocalDateTime deadline = this.task.getDeadlineProperty().getValue();

        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
        this.time.getValueProperty().setValue(deadline.toLocalTime().format(timeformat));

        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("d.MMM yyyy", Locale.getDefault());
        this.date.getValueProperty().setValue(deadline.toLocalDate().format(dateformat));
    }
}

