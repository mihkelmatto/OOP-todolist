package models;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.github.robsonkades.uuidv7.UUIDv7;

public class TaskGroup {
    private final UUID taskGID;
    private String groupname;
    private User owner;
    private ArrayList<User> users;
    private ArrayList<Task> tasks;

    public TaskGroup(User owner, Task... tasks){
        this.taskGID = UUIDv7.randomUUID();
        this.groupname = "New Group";
        this.owner = owner;
        this.users = new ArrayList<>();
        this.tasks = new ArrayList<>();

        this.users.add(owner);
        this.tasks.addAll(List.of(tasks));
    }

    public void setGroupname(String groupname){
        this.groupname = groupname;
    }

    public void addTasks(Task... tasks){
        this.tasks.addAll(List.of(tasks));
    }

    public void addUsers(User... users){
        this.users.addAll(List.of(users));
    }

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
