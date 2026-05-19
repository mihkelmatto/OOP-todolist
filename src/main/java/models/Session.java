package models;

import utils.Classreader;

import java.io.IOException;
import java.util.UUID;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
    private ObservableList<TaskGroup> taskgroups;
    private SimpleObjectProperty<TaskGroup> activeTG;


    /*
    SortedListi ei saa otse muuta.
    - lisamine ja eemaldamine taskgroups alt.
    - get() ja sarnased meetodid taskgroups alt
    */
   
    public Session(String username){
        this.user = Classreader.findUser(username);
        this.taskgroups = FXCollections.observableArrayList(Classreader.findTaskgroups(this.user.getID()));
        this.activeTG = new SimpleObjectProperty<TaskGroup>(this.taskgroups.get(0));
        FXCollections.sort(this.taskgroups);
    }

    // salvestamise ajal vist ei pea tgmapperit kontrollima?
    public void save(){
        this.user.toJsonFile();
        for(TaskGroup tg : taskgroups){
            tg.toJsonFile();
        }
    }
    /*
    Loob uue TaskGroupi:
    - uuendab this.taskgroups nimekirja
    - uuendab kasutaja TGmapperit
    */
    public void createTaskgroup(){
        try{
            TaskGroup tg = new TaskGroup(this.user.getID());
            tg.setGroupname("New task group");
            this.taskgroups.add(tg);

            UserTgMapper mapper = Classreader.fromJsonFile(this.user.getID(), UserTgMapper.class);
            mapper.addTaskgroups(tg.getID());
            mapper.toJsonFile();
        }
        catch(IOException e){
            e.printStackTrace(); // ei tohiks juhtuda, kuna taskgroup luuakse sisselogimisel
        }
    }

    /*
    Kustutab hetkel aktiivse taskgroupi.
    2. iga kasutaja taskmapper lugeda ja uuendada.
    4. taskgroup kustutada
    */
    public void deleteTaskgroup(){
        if(this.taskgroups.size() == 1){
            System.out.println("Viimast gruppi ei saa kustutada");
            return;
        }

        // uuendab iga this.activeTG-s oleva kasutaja TGmapperit
        UUID activeTGtgid = this.activeTG.getValue().getID();
        try{
            for(UUID userid : this.activeTG.getValue().getUsers()){
                UserTgMapper mapper = Classreader.fromJsonFile(userid, UserTgMapper.class);
                mapper.removeTaskgroup(activeTGtgid);
                mapper.toJsonFile();
            }
        }
        catch(IOException e){
            e.printStackTrace(); // ei tohiks juhtuda, kuna taskgroup luuakse sisselogimisel
        }
        this.taskgroups.remove(this.activeTG.getValue());
        this.activeTG.set(this.taskgroups.get(0));
    }

    // GETTERS
    public SimpleObjectProperty<TaskGroup> getActiveTGProperty(){
        return this.activeTG;
    }
    
    public User getUser(){
        return this.user;
    }

    public ObservableList<TaskGroup> getTaskgroupProperty(){
        return this.taskgroups;
    }
}
