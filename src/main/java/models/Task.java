package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.robsonkades.uuidv7.UUIDv7;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Task implements Comparable<Task>{
    private final UUID id;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private ObjectProperty<LocalDateTime> deadline;
    private ObjectProperty<LocalDateTime> lastupdated; // TODO: kas on vaja?

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

    @Override
    public int compareTo(Task task) {
        return this.getDeadline().compareTo(task.getDeadline());
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

    public void updateDeadline(String time, String date){
        time = time.strip();
        date = date.strip();

        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH.mm");
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        try{
            this.deadline.setValue(LocalDateTime.of(LocalDate.parse(date, dateformat), LocalTime.parse(time, timeformat)));
            this.lastupdated.set(LocalDateTime.now());
        } catch(Exception e){
            System.out.println("UpdateDeadLine: Invalid format");
        }
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
    public ObjectProperty<LocalDateTime> getDeadlineProperty(){
        return this.deadline;
    }
  
    @JsonIgnore
    public ObjectProperty<LocalDateTime> getLastupdatedProperty() {
        return this.lastupdated;
    }

    public UUID getID(){
        return this.id;
    }

    @Deprecated
    public String getTitle(){
        return this.title.getValue();
    }

    @Deprecated
    public String getDescription(){
        return this.description.getValue();
    }

    @Deprecated
    public LocalDateTime getDeadline(){
        return this.deadline.getValue();
    }

    @Deprecated
    public LocalDateTime getLastupdated(){
        return this.lastupdated.getValue();
    }
}
