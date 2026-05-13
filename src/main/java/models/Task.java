package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.robsonkades.uuidv7.UUIDv7;

public class Task {
    private final UUID id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime lastupdated;

    public Task(String title, String description, LocalDateTime deadline){
        this.id = UUIDv7.randomUUID();
        this.title = title;
        this.description = description;
        this.lastupdated = LocalDateTime.now();
        this.deadline = deadline;
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
        this.title = title;
        this.description = description;
        this.deadline = deadline;
        this.lastupdated = lastupdated;
    }

    // SETTERS

    public void updateTitle(String title){
        this.title = title;
        this.lastupdated = LocalDateTime.now();
    }

    public void updateDescription(String description){
        this.description = description;
        this.lastupdated = LocalDateTime.now();
    }

    public void updateDeadline(LocalDateTime deadline){
        this.deadline = deadline;
        this.lastupdated = LocalDateTime.now();
    }

    // GETTERS

    public UUID getID(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }

    public LocalDateTime getDeadline(){
        return this.deadline;
    }

    public LocalDateTime getLastupdated(){
        return this.lastupdated;
    }

    // OTHER

    @Override
    public String toString(){
        return String.format("""
                Title: %s
                Description: %s
                Deadline: %s
                Last updated: %s

                """, this.title, this.description, this.deadline, this.lastupdated);
    }

    public String formattedDeadline(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
        return this.deadline.format(formatter);
    }
}
