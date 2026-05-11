package utils;
import models.TaskGroup;
import models.User;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.robsonkades.uuidv7.UUIDv7;



public class Files {
    static String userspath = "src/main/data/users/";
    static String tgrouppath = "src/main/data/taskgroups/";
    static ObjectMapper mapper = new ObjectMapper();
    // TODO: Mis pathile faile salvestada?

    public static void main(String[] args) {
        // toJsonFile(models.TestData.getTestgroup());
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
        String path = String.format("%s%s.json", tgrouppath, group.getID()); 

        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), group);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    // FROMJSONFILE

    public static User fromJsonFile(String username) {
        String path = String.format("%s%s.json", userspath, username);

        try {
            return mapper.readValue(new File(path), User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
