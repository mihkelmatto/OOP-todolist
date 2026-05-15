package models;

import utils.Classreader;

import java.util.HashMap;
import java.util.UUID;

/*
Sessioon peaks hakkama hoidma kõiki instantse, mida UI kasutab.
Igal kasutajal on vähemalt üks taskgroup ja täpselt üks tgMapper.

Sessioon saab sisendiks kasutajanime. Selle järgi laeb failist User, Taskgroup (ja task) instantsid.

Kui kasutajat ei leidu:
- luua uus User(String username)
- luua uus taskgroup(UUID owner) ning lisada taskgroupide nimekirja

Programmi sulgemisel:
Salvestada User
Salvestada Taskgroupid
Lugeda uus TGmapper
- kui ei leidu, siis teha uus
Salvestada uus TGmapper
*/

public class Session {
    private User user;
    private HashMap<UUID, TaskGroup> taskgroups;

    public Session(String username){
        this.user = Classreader.findUser(username);
        this.taskgroups = Classreader.findTaskgroups(this.user.getID());
    }

    // salvestamise ajal vist ei pea tgmapperit kontrollima?
    public void save(){
        this.user.toJsonFile();
        for(UUID tgid : taskgroups.keySet()){
            taskgroups.get(tgid).toJsonFile();
        }
    }
    // SETTERS
    // GETTERS
    public User getUser(){
        return this.user;
    }

    public HashMap<UUID, TaskGroup> getTaskgroups(){
        return this.taskgroups;
    }
    // OTHER

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Session info: \n");
        sb.append(user.toString());
        for(UUID tgid : taskgroups.keySet()) sb.append(taskgroups.get(tgid).toString() + "\n");
        return sb.toString();
    }
}
