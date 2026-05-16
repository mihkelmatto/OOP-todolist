package models;

import utils.Classreader;
import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;

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
    private ArrayList<TaskGroup> taskgroups;  // TODO: Sorteerimine
    private SimpleObjectProperty<TaskGroup> activeTG;

    public Session(String username){
        this.user = Classreader.findUser(username);
        this.taskgroups = Classreader.findTaskgroups(this.user.getID());
        this.activeTG = new SimpleObjectProperty<TaskGroup>(this.taskgroups.get(0));
    }

    // salvestamise ajal vist ei pea tgmapperit kontrollima?
    public void save(){
        this.user.toJsonFile();
        for(TaskGroup tg : taskgroups){
            tg.toJsonFile();
        }
    }
    // SETTERS

    // GETTERS
    public SimpleObjectProperty<TaskGroup> getActiveTGProperty(){
        return this.activeTG;
    }
    
    public User getUser(){
        return this.user;
    }

    public ArrayList<TaskGroup> getTaskgroups(){
        return this.taskgroups;
    }
}
