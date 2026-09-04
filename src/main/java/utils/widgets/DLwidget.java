package utils.widgets;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import models.Session;
import models.Task;
import models.TaskGroup;
import utils.validators.DateValidator;
import utils.validators.TimeValidator;

public class DLwidget extends HBox{
    private Session session;
    private Task task;
    private ObjectProperty<LocalDateTime> deadlineProperty;
    
    private EditableField time;
    private EditableField date;

    private SimpleStringProperty timestr;
    private SimpleStringProperty datestr;

    public DLwidget(Session session, Task task){
        // field init
        this.session = session;
        this.task = task;
        this.deadlineProperty = task.getDeadlineProperty();
        this.timestr = new SimpleStringProperty(getFormattedTime());
        this.datestr = new SimpleStringProperty(getFormattedDate());

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
        
        this.time = new EditableField(this.timestr, "deadline-time");
        this.time.setValidator(new TimeValidator());
        this.time.getValueField().setPromptText("HH.mm");
        time.getStyleClass().add("deadline");

        this.date = new EditableField(this.datestr, "deadline-date");
        this.date.setValidator(new DateValidator());
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
            
            this.task.updateDeadline(LocalDate.parse(this.date.getValue(), getDateformat()),LocalTime.parse(this.time.getValue(), getTimeformat()));
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
        LocalDateTime deadline = this.deadlineProperty.getValue();

        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH:mm");
        this.time.setValue(deadline.toLocalTime().format(timeformat));

        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("d.MMM yyyy", Locale.getDefault());
        this.date.setValue(deadline.toLocalDate().format(dateformat));
    }

    /*
        Konkreetse objekti aja tagastamine Stringi kujul
    */

    private String getFormattedTime(){
        return this.deadlineProperty.getValue().toLocalTime().format(getTimeformat());
    }

    private String getFormattedDate(){
        return this.deadlineProperty.getValue().toLocalDate().format(getDateformat());
    }

    /*
        Annab erinevatele komponentidele formaadi, milles kasutajaliides aega näitab
    */

    public static DateTimeFormatter getTimeformat(){
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    public static DateTimeFormatter getDateformat(){
        return DateTimeFormatter.ofPattern("d.MMM yyyy", Locale.getDefault());
    }
}

