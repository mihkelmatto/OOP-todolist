package models;
import utils.ToJson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.robsonkades.uuidv7.UUIDv7;
import java.util.UUID;


/*
Username peaks ka olema unikaalne, et sisselogimisel andmed üles leida.
Siiski on vist mõistlik mujal UUID-d kasutada, et nimevahetus oleks tehniliselt lihtsam.

TODO: Username loomisel või vahetamisel unikaalsuse kontroll
*/ 

public class User implements ToJson{ 
    private final UUID id;
    private String username;

    public User(String username){
        this.id = UUIDv7.randomUUID();
        this.username = username;
        System.out.printf("New user created: %s, ID: %s\n", this.username, this.id);
    }

    @JsonCreator
    public User(
            @JsonProperty("uuid") UUID id, 
            @JsonProperty("username") String username
        ) {
        this.id = id;
        this.username = username;
    }    

    // SETTERS
    public void setUsername(String username){
        this.username = username;
    }

    // GETTERS

    public UUID getID(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    // OTHER
    
    @Override
    public String toString(){
        return String.format("Username: %s, UUID: %s\n", this.username, this.id);
    }
}