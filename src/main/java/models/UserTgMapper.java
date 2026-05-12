package models;
import utils.Filesrw;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.UUID;


/*
TODO
Seab vastavusse User ja TaskGroup instantsid


Kui kasutaja kustutab taskgroupi:
1. iga task läheb default gruppi
2. iga kasutaja taskmapper lugeda ja uuendada.
3. enda taskmapperist vastav grupp kustutada
4. taskgroup kustutada

kui omanik eemaldab isiku taskgroupist:
1. kas on omanik?
2. vastava kasutaja taskmapper uuendada
3. taskgroupist eemaldada kasutaja UUID


*/

public class UserTgMapper implements Filesrw{
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

    public static void main(String[] args) {
        User user = Filesrw.fromJsonFile(UUID.fromString("019e1916-2931-7c32-b749-2b01ee8263db"), User.class);
        UUID[] groups = {
            UUID.fromString("019e18db-97fe-7778-a048-8016ed04acfc"),
            UUID.fromString("019e191b-23c7-724b-ba57-797c5bf425be")
        };


        UserTgMapper tgmapper = new UserTgMapper(user.getID(), groups);
        UUID uuid = tgmapper.getID();


        tgmapper.toJsonFile();

        UserTgMapper fromfile = Filesrw.fromJsonFile(uuid, UserTgMapper.class);
        System.out.printf(fromfile.toString());
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

    public void removeTaskgroup(UUID tg){

    }

    @Override
    public String toString(){
        return String.format("user id: %s \nGroups: %s", this.id, this.taskgroups);
    }
}
