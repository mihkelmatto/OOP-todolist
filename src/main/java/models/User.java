package models;
import utils.ToJson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.robsonkades.uuidv7.UUIDv7;
import javafx.beans.property.SimpleStringProperty;

import java.util.UUID;


/*
Username peaks ka olema unikaalne, et sisselogimisel andmed üles leida.
Siiski on vist mõistlik mujal UUID-d kasutada, et nimevahetus oleks tehniliselt lihtsam.

TODO: Username vahetamisel unikaalsuse kontroll
*/ 

public class User implements ToJson{ 
    private final UUID id;
    private SimpleStringProperty username;
    private String password;

    public User(String username, String password){
        this.id = UUIDv7.randomUUID();
        this.username = new SimpleStringProperty(username);
        this.password = password;
        System.out.printf("New user created: %s, ID: %s\n", this.username, this.id);
    }

    @JsonCreator
    public User(
            @JsonProperty("uuid") UUID id, 
            @JsonProperty("username") String username,
            @JsonProperty("password") String password
        ) {
        this.id = id;
        this.username = new SimpleStringProperty(username);
        this.password = password;
    }    

    // SETTERS
    public void setUsername(String username){
        this.username.set(username);
    }

    public void setPassword(String password){
        this.password = password;
    }

    // GETTERS

    @JsonIgnore
    public SimpleStringProperty getUsernameProperty(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
    
    public UUID getID(){
        return this.id;
    }
    
    @Deprecated
    public String getUsername(){
        return this.username.getValue();
    }
}