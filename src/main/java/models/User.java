package models;

import java.util.UUID;
import io.github.robsonkades.uuidv7.UUIDv7;

public class User {
    private final UUID userID;
    private String username;

    public User(String username){
        this.userID = UUIDv7.randomUUID();
        this.username = username;
    }

    @Override
    public String toString(){
        return this.username;
    }
}
