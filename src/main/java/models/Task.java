package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
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

    @Override
    public String toString(){
        return String.format("""
                Title: %s
                Description: %s
                Deadline: %s
                Last updated: %s

                """, this.title, this.description, this.deadline, this.lastupdated);
    }

    public String getTitle(){
        return this.title;
    }
    
    public String getDescription(){
        return this.description;
    }

    public String getFormattedDeadline(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");
        return this.deadline.format(formatter);
    }

    
}
