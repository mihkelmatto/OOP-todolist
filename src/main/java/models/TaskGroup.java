package models;

import utils.Classreader;
import utils.ToJson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import io.github.robsonkades.uuidv7.UUIDv7;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskGroup implements ToJson, Comparable<TaskGroup> {
    private final UUID id;
    private StringProperty title;
    private UUID owner;
    private ArrayList<UUID> users;
    private ObservableList<Task> tasks;

    public TaskGroup(UUID owner, Task... tasks){
        this.id = UUIDv7.randomUUID();
        this.title = new SimpleStringProperty("New Group");
        this.owner = owner;
        this.users = new ArrayList<>();

        this.tasks = FXCollections.observableArrayList();
        this.tasks.addAll(List.of(tasks));

        this.users.add(owner);
    }

    @JsonCreator
    public TaskGroup(
            @JsonProperty("id") UUID id,
            @JsonProperty("title") String title,
            @JsonProperty("owner") UUID owner,
            @JsonProperty("users") ArrayList<UUID> users,
            @JsonProperty("tasks") ArrayList<Task> tasks
    ) {
        this.id = id;
        this.title = new SimpleStringProperty(title);
        this.owner = owner;
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
        this.tasks = FXCollections.observableArrayList();
        this.tasks.addAll(tasks);
    }

    @Override
    public int compareTo(TaskGroup tg) {
        return this.title.getValue().compareToIgnoreCase(tg.title.getValue());
    }

    // SETTERS
    public void setTitle(String title){
        this.title.set(title);
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
            System.out.println("Omanikku ei saa eemaldada. Selle asemel tuleb grupp kustutada.");
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
        this.tasks.addFirst(task);
    }

    public void removeTask(Task task){
        this.tasks.remove(task);
    }

    // GETTERS
    @JsonIgnore
    public StringProperty getTitleProperty(){
        return this.title;
    }

    @JsonIgnore
    public ObservableList<Task> getTasksProperty(){
        return this.tasks;
    }
    
    public UUID getID(){
        return this.id;
    }

    @Deprecated
    public String getTitle(){
        return this.title.getValue();
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
