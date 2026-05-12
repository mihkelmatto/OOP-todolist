package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.github.robsonkades.uuidv7.UUIDv7;
import utils.Filesrw;

public class TaskGroup implements Filesrw{
    private final UUID id;
    private String groupname;
    private UUID owner;
    private ArrayList<UUID> users;
    private ArrayList<Task> tasks;

    public TaskGroup(UUID owner, Task... tasks){
        this.id = UUIDv7.randomUUID();
        this.groupname = "New Group";
        this.owner = owner;
        this.users = new ArrayList<>();
        this.tasks = new ArrayList<>();

        this.users.add(owner);
        this.tasks.addAll(List.of(tasks));
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
        this.groupname = groupname;
        this.owner = owner;
        this.users = users != null ? new ArrayList<>(users) : new ArrayList<>();
        this.tasks = tasks != null ? new ArrayList<>(tasks) : new ArrayList<>();
    }

    // SETTERS

    public void setGroupname(String groupname){
        this.groupname = groupname;
    }

    public void addUsers(UUID... users){
        this.users.addAll(List.of(users));
    }

    public void addTasks(Task... tasks){
        this.tasks.addAll(List.of(tasks));
    }

    // GETTERS

    public UUID getID(){
        return this.id;
    }

    public String getGroupname(){
        return this.groupname;
    }

    public UUID getOwner(){
        return this.owner;
    }

    public ArrayList<UUID> getUsers(){
        return this.users;
    }

    public ArrayList<Task> getTasks(){
        return this.tasks;
    }

    // OTHER

    @Override
    public String toString(){
        return String.format("""
                    Task group: %s
                    Owner: %s
                    Users: %s
                    Task count: %s
                """, this.groupname, this.owner, this.users, this.tasks.size());
    }

    public String tasksToString(){
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Task list for group %s:\n", this.groupname));
        for(Task task : this.tasks) sb.append(task);
        sb.append("\n");
        return sb.toString();
    }
}
