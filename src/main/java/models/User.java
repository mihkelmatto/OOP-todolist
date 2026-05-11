package models;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.robsonkades.uuidv7.UUIDv7;

/*
Username peaks ka olema unikaalne, et sisselogimisel andmed üles leida.
Siiski on vist mõistlik mujal UUID-d kasutada, et nimevahetus oleks tehniliselt lihtsam.

TODO: Username loomisel või vahetamisel unikaalsuse kontroll
*/ 

public class User { 
    private final UUID userID;
    private String username;

    public User(String username){
        this.userID = UUIDv7.randomUUID();
        this.username = username;
    }

    @JsonCreator
    public User(
            @JsonProperty("uuid") UUID userID, 
            @JsonProperty("username") String username
        ) {
        this.userID = userID;
        this.username = username;
    }    

    // SETTERS
    public void setUsername(String username){
        this.username = username;
    }

    // GETTERS

    public UUID getUUID(){
        return this.userID;
    }

    public String getUsername(){
        return this.username;
    }

    // OTHER
    
    @Override
    public String toString(){
        return this.username;
    }




}
