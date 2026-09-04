package UI.Home;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import utils.validators.DateValidator;
import utils.validators.TimeValidator;
import utils.widgets.EditableField;

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

    public DLwidget(LocalDateTime deadline){
        String time = deadline.toLocalTime().format(getTimeformat());
        String date = deadline.toLocalDate().format(getDateformat());

        this.time = new EditableField(time, "deadline-time");
        this.time.setValidator(new TimeValidator());
        this.time.getValueField().setPromptText("HH.mm");

        this.date = new EditableField(date, "deadline-date");
        this.date.setValidator(new DateValidator());
        this.date.getValueField().setPromptText("dd.MM.yyyy");

        this.datetimeProperty = new SimpleObjectProperty<>(deadline);

        initLayout();

        // events / listeners

        this.time.getValueProperty().addListener(e -> {
            updateDatetimeProperty();
        });

        this.date.getValueProperty().addListener(e -> {
            updateDatetimeProperty();
        });
    }

    private void initLayout(){
        this.setSpacing(20);
        Region clockicon = new Region();
        clockicon.getStyleClass().add("clockicon");
        
        VBox timebox = new VBox();
        timebox.setAlignment(Pos.CENTER_LEFT);
        timebox.getChildren().addAll(this.time, this.date);

        this.getChildren().addAll(clockicon, timebox);

        // css
        this.setId("deadlinewidget");
        this.time.getStyleClass().add("deadline"); 
        this.date.getStyleClass().add("deadline");
    }

    public void setEditable(boolean editable){
        this.time.setEditable(editable);
        this.date.setEditable(editable);
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
    public ObjectProperty<LocalDateTime> getTimeProperty(){
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

