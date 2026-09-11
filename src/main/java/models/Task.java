package models;

import io.github.robsonkades.uuidv7.UUIDv7;

import java.time.LocalDateTime;
import java.util.UUID;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Task implements Comparable<Task>{
    private final UUID id;
    private SimpleStringProperty title;
    private SimpleStringProperty description;
    private SimpleObjectProperty<LocalDateTime> deadline;
    private SimpleObjectProperty<LocalDateTime> lastupdated;

    private boolean isnew;

    public Task(){
        this("New Task", "Description", LocalDateTime.of(2025, 1, 1, 0, 0));
    }

    public Task(String title, String description, LocalDateTime deadline){
        this.id = UUIDv7.randomUUID();
        this.title = new SimpleStringProperty(title);
        this.description = new SimpleStringProperty(description);
        this.deadline = new SimpleObjectProperty<>(deadline);
        this.lastupdated = new SimpleObjectProperty<>(LocalDateTime.now());
        this.isnew = true;
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
        this.isnew = false;
    }

    // ajaline -> pealkirja tähestikuline järjestus
    @Override
    public int compareTo(Task task) {
        int result = this.getDeadline().compareTo(task.getDeadline());
        
        if (result != 0) {
            return result;
        }

        return this.getTitle().compareToIgnoreCase(task.getTitle());
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

    public void updateDeadline(LocalDateTime dt){
        this.deadline.setValue(dt);
        this.lastupdated.set(LocalDateTime.now());
    }

    public void consumeNew(){
        this.isnew = false;
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
    public SimpleObjectProperty<LocalDateTime> getDeadlineProperty(){
        return this.deadline;
    }
  
    @JsonIgnore
    public SimpleObjectProperty<LocalDateTime> getLastupdatedProperty() {
        return this.lastupdated;
    }

    @JsonIgnore 
    public boolean isnew(){
        return this.isnew;
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
