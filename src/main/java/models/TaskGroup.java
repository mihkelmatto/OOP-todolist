package models;

import utils.Classreader;
import utils.ToJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.robsonkades.uuidv7.UUIDv7;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.SortedList;


/*
TODO: Userite nimekirja peaks ehk Setiks tegema, et ei peaks duplikaate kontrollima ning otsimisaeg oleks väiksem (Setil on vist O1)
TODO: Jackson getteritelt field-accessi peale
TODO: Sisu halduse Error handling? Peaks olema tegelikult varasemalt garanteeritud.
*/

public class TaskGroup implements ToJson, Comparable<TaskGroup> {
    private final UUID id;
    private SimpleStringProperty groupname;
    private UUID owner;
    private ArrayList<UUID> users;
    private SortedList<Task> tasks;

    public TaskGroup(UUID owner, Task... tasks){
        this.id = UUIDv7.randomUUID();
        this.groupname = new SimpleStringProperty("New Group");
        this.owner = owner;
        this.users = new ArrayList<>();
        this.tasks = new SortedList<Task>(FXCollections.observableArrayList()); // TODO: ei sorteeri aja muudatuste peale

        this.users.add(owner);
        this.tasks.addAll(List.of(tasks));

        System.out.printf("New Taskgroup created for user: %s\n", owner);
    }

    @JsonCreator
    public TaskGroup(
            @JsonProperty("id") UUID id,
            @JsonProperty("groupname") String groupname,
            @JsonProperty("owner") UUID owner,
            @JsonProperty("users") ArrayList<UUID> users,
            @JsonProperty("tasks") ArrayList<Task> tasks
    ) {
        this.id = id;
        this.groupname = new SimpleStringProperty(groupname);
        this.owner = owner;
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
        this.tasks = new SortedList<Task>(FXCollections.observableArrayList(tasks != null ? tasks : new ArrayList<>()));
    }

    @Override
    public int compareTo(TaskGroup tg) {
        return this.groupname.getValue().compareToIgnoreCase(tg.groupname.getValue());
    }

    // SETTERS
    public void setGroupname(String groupname){
        this.groupname.set(groupname);
    }

    /*
    Lisab isiku taskgroupi
    !!! eeltingimus: kasutaja eksisteerib ning ei ole juba taskgroupi lisatud

    1. kas kasutaja on juba olemas?
    2. lisada taskgroup.users nimekirja
    3. uuendada kasutaja TGmapperit

    
    */
    public void addUser(UUID userid){
        try{
            UserTgMapper mapper = Classreader.fromJsonFile(userid, UserTgMapper.class);
            this.users.add(userid);
            mapper.getTaskgroups().add(this.id);
            mapper.toJsonFile();
        }
        catch(IOException e){
            e.printStackTrace(); 
        }
    }

    /*
    eemaldab isiku taskgroupist
    !! eeltingimus: kasutaja eksisteerib taskgroupis.

    1. omanikku ei saa eemaldada
    2. vastava kasutaja taskmapper uuendada
    3. taskgroupist eemaldada kasutaja UUID
    */

    public void removeUser(UUID userid){
        if(userid.equals(this.owner)){
            System.out.println("Omanikku ei saa eemaldada. Selle asemel tuleb grupp kustutada."); // TODO: mida siis UI-s teha?
        }
        else{
            try{
                UserTgMapper mapper = Classreader.fromJsonFile(userid, UserTgMapper.class);
                this.users.remove(userid);
                mapper.getTaskgroups().remove(this.id);
                mapper.toJsonFile();
            }
            catch(IOException e){
                e.printStackTrace(); 
            }
        }
    }
    
    public void addTask(Task task){
        this.tasks.add(task);
    }

    // GETTERS
    @JsonIgnore
    public SimpleStringProperty getGroupnameProperty(){
        return this.groupname;
    }

    @JsonIgnore
    public SortedList<Task> getTasksProperty(){
        return this.tasks;
    }
    
    public UUID getID(){
        return this.id;
    }

    @Deprecated
    public String getGroupname(){
        return this.groupname.getValue();
    }

    @Deprecated
    public UUID getOwner(){
        return this.owner;
    }

    public ArrayList<UUID> getUsers(){
        return this.users;
    }

    @Deprecated
    public ArrayList<Task> getTasks(){
        return new ArrayList<>(this.tasks);
    }
}
