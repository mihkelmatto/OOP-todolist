package UI.Home;

import utils.validators.datetime.DateValidator;
import utils.validators.datetime.TimeValidator;
import utils.widgets.EditableField;
import utils.widgets.svg.SVGIcon;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
    Näitab kuupäeva ja kellaaega ning võimaldab seda muudetavaks teha setEditable() abil
    Sisend on valideeritud EditableFieldis vastavalt siin klassis antud ajaformaadile

    Hoiab oma andmeid ObjectProperty<LocalDateTime> sees, mis uueneb vastavalt time, date valideeritud sisendile.
    Väljaspool DLwidgeti klassi sisendi kasutamiseks on vastavad getterid

    !! Uuenduste jm üheks eelduseks on, et time ja date on samal ajal editable.
*/

public class DLwidget extends HBox{
    private EditableField time;
    private EditableField date;

    private ObjectProperty<LocalDateTime> datetimeProperty;
    private boolean editable;

    public DLwidget(LocalDateTime deadline){
        String time = deadline.toLocalTime().format(getTimeformat());
        String date = deadline.toLocalDate().format(getDateformat());

        this.time = new EditableField(time);
        this.time.setValidator(new TimeValidator());
        this.time.getValueField().setPromptText(getTimePattern());

        this.date = new EditableField(date);
        this.date.setValidator(new DateValidator());
        this.date.getValueField().setPromptText(getDatePattern());

        this.datetimeProperty = new SimpleObjectProperty<>(deadline);
        this.editable = false;

        initLayout();

        // events / listeners

    }

    private void initLayout(){
        this.setSpacing(20);
 
        SVGIcon clockicon = new SVGIcon("clockicon.path");
        clockicon.getStyleClass().add("clockicon");
        
        VBox timebox = new VBox();
        timebox.setAlignment(Pos.CENTER_LEFT);
        timebox.getChildren().addAll(this.time, this.date);

        this.getChildren().addAll(clockicon, timebox);

        // css
        this.setId("deadlinewidget");
        this.time.getStyleClass().add("deadline"); 
        this.date.getStyleClass().add("deadline");
        this.time.getStyleClass().add("deadline-time");
        this.date.getStyleClass().add("deadline-date");
    }

    public void setEditable(boolean editable){
        this.editable = editable;
        this.time.setEditable(editable);
        this.date.setEditable(editable);

        if(!editable){
            updateDatetimeProperty();
        }
    }

    public void updateDatetimeProperty(){
        if(time.isEditable() || date.isEditable()) return;
        else{
            LocalDateTime dt = LocalDateTime.of(
                LocalDate.parse(this.date.getValue(), getDateformat()),
                LocalTime.parse(this.time.getValue(), getTimeformat())
            );
            this.datetimeProperty.setValue(dt);
        }
    }
    // GETTERS
    public ObjectProperty<LocalDateTime> getDateTimeProperty(){
        return this.datetimeProperty;
    }

    public LocalDateTime getDateTime(){
        return this.datetimeProperty.getValue();
    }

    public LocalTime getTime(){
        return this.datetimeProperty.getValue().toLocalTime();
    }

    public LocalDate getDate(){
        return this.datetimeProperty.getValue().toLocalDate();
    }

    public boolean isEditable(){
        return this.editable;
    }

    /*
        Annab erinevatele komponentidele formaadi, milles kasutajaliides aega näitab
    */
    public static String getDatePattern(){
        return "d.MMM.yyyy";
    }

    public static String getTimePattern(){
        return "HH:mm";
    }

    public static DateTimeFormatter getTimeformat(){
        return DateTimeFormatter.ofPattern(getTimePattern());
    }

    public static DateTimeFormatter getDateformat(){
        return DateTimeFormatter.ofPattern(getDatePattern());
    }
}

