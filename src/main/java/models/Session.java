package models;

import utils.Classreader;

import java.io.IOException;
import java.util.UUID;

import javafx.beans.property.ObjectProperty;
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
    private ObjectProperty<TaskGroup> activeTGProperty;


    /*
    ObservableListi ei saa otse muuta.
    - lisamine ja eemaldamine taskgroups alt.
    - get() ja sarnased meetodid taskgroups alt
    */

    public Session(User user){
        this.user = user;
        this.taskgroups = FXCollections.observableArrayList(Classreader.findTaskgroups(this.user.getID()));
        this.activeTGProperty = new SimpleObjectProperty<TaskGroup>(this.taskgroups.get(0));
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
    public TaskGroup createTaskgroup(){
        try{
            TaskGroup tg = new TaskGroup(this.user.getID());
            tg.setTitle("New task group");
            this.taskgroups.add(tg);
            this.activeTGProperty.setValue(tg);
            
            UserTgMapper mapper = Classreader.fromJsonFile(this.user.getID(), UserTgMapper.class);
            mapper.addTaskgroups(tg.getID());
            mapper.toJsonFile();

            return tg;
        }
        catch(IOException e){
            e.printStackTrace(); // ei tohiks juhtuda, kuna taskgroup luuakse sisselogimisel
            return null;
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
        TaskGroup activeTG = this.activeTGProperty.getValue();
        try{
            for(UUID userid : activeTG.getUsers()){
                UserTgMapper mapper = Classreader.fromJsonFile(userid, UserTgMapper.class);
                mapper.removeTaskgroup(activeTG.getID());
                mapper.toJsonFile();
            }
            Classreader.deleteJsonFile(activeTG.getID(), TaskGroup.class);
        }
        catch(IOException e){
            e.printStackTrace(); // ei tohiks juhtuda, kuna taskgroup luuakse sisselogimisel
        }
        this.taskgroups.remove(activeTG);

        TaskGroup newactive = this.taskgroups.get(0);
        this.activeTGProperty.set(newactive);
    }

    // GETTERS
    public ObjectProperty<TaskGroup> getActiveTGProperty(){
        return this.activeTGProperty;
    }
    
    public User getUser(){
        return this.user;
    }

    public ObservableList<TaskGroup> getTGListProperty(){
        return this.taskgroups;
    }
}