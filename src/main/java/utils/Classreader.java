package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import models.TaskGroup;
import models.User;
import models.UserTgMapper;

/*
Leida Stringi järgi user

Kui ei leidu:
- luua uus User(String username)
- luua uus taskgroup(UUID owner)

Programmi sulgemisel:
Salvestada User
Salvestada Taskgroupid
Lugeda uus TGmapper
- kui ei leidu, siis teha uus
Salvestada uus TGmapper
*/

public class Classreader {
    static String path = "src/main/data";
    static ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    public static void main(String[] args) throws Exception{
        User user = findUser("Mari");
        UserTgMapper tg = fromJsonFile(user.getID(), UserTgMapper.class);
        
        ArrayList<TaskGroup> taskgroups = new ArrayList<>();
        for(UUID tgid : tg.getTaskgroups()) taskgroups.add(fromJsonFile(tgid, TaskGroup.class));

        System.out.println(taskgroups);
    }

    // Leiab data/User kaustast vastava kasutaja. Kui kasutajat ei leidu, siis teeb uue.
    public static User findUser(String username){
        File dir = new File(getDir(User.class));
        File[] files = dir.listFiles();

        if(files == null){
            System.out.printf("User %s not found. Creating a new instance.\n", username);
            return new User(username);
        }

        for(File f : files){
            try{
                User user = mapper.readValue(f, User.class);
                if(user.getUsername().equals(username)) return user;
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
        System.out.printf("User %s not found. Creating a new instance.\n", username);
        return new User(username);
    }

    // Kasutaja leidmiseks on meetod: findUser(String username)
    public static <T extends ToJson> T fromJsonFile(UUID uuid, Class<T> clazz) throws IOException{
        File file = new File(String.format("%s/%s.json", getDir(clazz), uuid));

        if (!file.isFile()) throw new FileNotFoundException("File not found: " + file);
        return mapper.readValue(file, clazz);
    }

    // GETTERS

    // Tagastab directory, kuhu failid salvestatakse
    public static <T extends ToJson> String getDir(Class<T> clazz){
        return String.format("%s/%s/", path, clazz.getSimpleName());
    }

    // Vajalik Jacksoni moodulile ToJson ja Classreader klassides 
    public static ObjectMapper getMapper(){
        return mapper;
    }
}
