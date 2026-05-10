package models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import io.github.robsonkades.uuidv7.UUIDv7;

public class Task {
    private final UUID taskID;
    private String title;
    private String description;

    private LocalDateTime deadline;
    private LocalDateTime lastupdated;

    private User assigneduser;
    // TODO: Kas peaks lisama TaskGroupi kohta ka märke?

    public Task(String title, String description, LocalDateTime deadline){
        this.taskID = UUIDv7.randomUUID();
        this.title = title;
        this.description = description;
        this.assigneduser = null;
        this.lastupdated = LocalDateTime.now();
        this.deadline = deadline;
        // TODO: mis formaadis deadline input tuleb? Ilmselt peaks tegema eraldi meetodi String -> LocalDateTime
    }

    @Override
    public String toString(){
        return String.format("""
                Title: %s
                Description: %s
                Assigned user: %s
                Deadline: %s
                Last updated: %s

                """, this.title, this.description, this.assigneduser, this.deadline, this.lastupdated);
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
