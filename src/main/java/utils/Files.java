package utils;
import models.TaskGroup;
import models.User;

import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Files {
    static String userspath = "src/main/data/users/";
    static String taskgpath = "src/main/data/taskgroups/";
    static ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    // TODO: Mis pathile faile salvestada?

    public static void main(String[] args) {

    }

    // TOJSONFILE

    // TODO: võiks panna failinimeks UUID, kuid siis tuleks teha eraldi mapping fail
    public static void toJsonFile(User user){
        String path = String.format("%s%s.json", userspath, user.getUsername()); 

        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), user);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void toJsonFile(TaskGroup group){
        String path = String.format("%s%s.json", taskgpath, group.getID()); 

        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), group);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // FROMJSONFILE

    public static User userFromJsonFile(String username) {
        String path = String.format("%s%s.json", userspath, username);

        try {
            return mapper.readValue(new File(path), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TaskGroup taskgroupFromJsonFile(String uuid){
        String path = String.format("%s%s.json", taskgpath, uuid);

        try {
            return mapper.readValue(new File(path), TaskGroup.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
