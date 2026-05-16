package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.robsonkades.uuidv7.UUIDv7;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task {
    private final UUID id;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private ObjectProperty<LocalTime> timeDL;
    private ObjectProperty<LocalDate> dateDL;
    private ObjectProperty<LocalDateTime> lastupdated; // TODO> kas on vaja?

    public Task(String title, String description, LocalDateTime deadline){
        this.id = UUIDv7.randomUUID();
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.timeDL = new SimpleObjectProperty<>(deadline.toLocalTime());
        this.dateDL = new SimpleObjectProperty<>(deadline.toLocalDate());
        this.lastupdated = new SimpleObjectProperty<>(LocalDateTime.now());
    }

    @JsonCreator
    public Task(
        @JsonProperty("id") UUID id,
        @JsonProperty("title") String title,
        @JsonProperty("description") String description,
        @JsonProperty("deadline") LocalDateTime deadline,
        @JsonProperty("lastupdated") LocalDateTime lastupdated
    ) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.timeDL = new SimpleObjectProperty<>(deadline.toLocalTime());
        this.dateDL = new SimpleObjectProperty<>(deadline.toLocalDate());
        this.lastupdated = new SimpleObjectProperty<>(lastupdated);
    }
    
    public String formattedTimeDL(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeDL.getValue().format(formatter);
    }

    public String formattedDateDL(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.MMM yyyy", Locale.getDefault());
        return dateDL.getValue().format(formatter);
    }

    /*
    Vana formatter:

    public String formattedDeadline(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm d.MMM yyyy", Locale.getDefault());
        return this.deadline.getValue().format(formatter);
    }
    */


    // SETTERS

    public void updateTitle(String title){
        this.title.set(title);
        this.lastupdated.set(LocalDateTime.now());
    }

    public void updateDescription(String description){
        this.description.set(description);
        this.lastupdated.set(LocalDateTime.now());
    }

    public void updateTimeDL(LocalTime timeDL){
        this.lastupdated.set(LocalDateTime.now());
        this.timeDL.set(timeDL);
    }

    public void updateDateDL(LocalDate dateDL){
        this.lastupdated.set(LocalDateTime.now());
        this.dateDL.set(dateDL);
    }

    public void updateDateTimeDL(LocalDateTime deadline){
        this.lastupdated.set(LocalDateTime.now());
        this.timeDL.set(deadline.toLocalTime());
        this.dateDL.set(deadline.toLocalDate());
    }

    // GETTERS
    
    @JsonIgnore
    public SimpleStringProperty getTitleProperty() {
        return this.title;
    }

    @JsonIgnore
    public SimpleStringProperty getDescriptionProperty() {
        return this.description;
    }

    @JsonIgnore
    public ObjectProperty<LocalTime> getTimeDLProperty() {
        return this.timeDL;
    }

    @JsonIgnore
    public ObjectProperty<LocalDate> getDateDLProperty() {
        return this.dateDL;
    }

    @JsonIgnore
    public ObjectProperty<LocalDateTime> getLastupdatedProperty() {
        return this.lastupdated;
    }

    public UUID getID(){
        return this.id;
    }
 
    public String getTitle(){
        return this.title.getValue();
    }

    public String getDescription(){
        return this.description.getValue();
    }

    public LocalDateTime getDeadline(){
        LocalDateTime deadline = LocalDateTime.of(dateDL.getValue(), timeDL.getValue());
        return deadline;
    }

    public LocalDateTime getLastupdated(){
        return this.lastupdated.getValue();
    }
}
