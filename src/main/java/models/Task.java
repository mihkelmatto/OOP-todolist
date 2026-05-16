package models;

import java.time.LocalDateTime;
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
    private ObjectProperty<LocalDateTime> deadline;
    private ObjectProperty<LocalDateTime> lastupdated;

    public Task(String title, String description, LocalDateTime deadline){
        this.id = UUIDv7.randomUUID();
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.deadline = new SimpleObjectProperty<>(deadline);
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
        this.deadline = new SimpleObjectProperty<>(deadline);
        this.lastupdated = new SimpleObjectProperty<>(lastupdated);
    }
    

    public String formattedDeadline(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm d.MMM yyyy", Locale.getDefault());
        return this.deadline.getValue().format(formatter);
    }


    // SETTERS

    public void updateTitle(String title){
        this.title.set(title);
        this.lastupdated.set(LocalDateTime.now());
    }

    public void updateDescription(String description){
        this.description.set(description);
        this.lastupdated.set(LocalDateTime.now());
    }

    public void updateDeadline(LocalDateTime deadline){
        this.lastupdated.set(LocalDateTime.now());
        this.deadline.set(deadline);
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
    public ObjectProperty<LocalDateTime> getDeadlineProperty() {
        return this.deadline;
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
        return this.deadline.getValue();
    }

    public LocalDateTime getLastupdated(){
        return this.lastupdated.getValue();
    }
}
