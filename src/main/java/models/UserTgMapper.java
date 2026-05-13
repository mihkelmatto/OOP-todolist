package models;
import utils.ToJson;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.UUID;


/*
Seab vastavusse User ja TaskGroup instantsid
*/

public class UserTgMapper implements ToJson{
    UUID id;    // !!!! tegemist on UserID-ga, kuid nimi on selline Filesrw jaoks !!!!
    ArrayList<UUID> taskgroups;
    UserTgMapper(UUID user, UUID ... taskgroups){
        this.id = user;
        this.taskgroups = new ArrayList<>();
        for(UUID tg : taskgroups) this.taskgroups.add(tg);
    }

    @JsonCreator
    public UserTgMapper(
            @JsonProperty("uuid") UUID id, 
            @JsonProperty("username") ArrayList<UUID> taskgroups
        ) {
        this.id = id;
        this.taskgroups = taskgroups;
    }

    // SETTERS


    // GETTERS
    public UUID getID(){
        return this.id;
    }

    public ArrayList<UUID> getTaskgroups(){
        return this.taskgroups;
    }
    // OTHER
    public void addTaskgroups(UUID ... taskgroups){
        for(UUID tg : taskgroups) this.taskgroups.add(tg);
    }

    public void removeTaskgroup(UUID taskgroup){
        this.taskgroups.remove(taskgroup);
    }
}
