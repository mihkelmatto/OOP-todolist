package utils;

import models.TaskGroup;
import models.User;
import models.UserTgMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class Classreader {
    static String path = "src/main/data";
    static ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    /*
    Leiab Stringi järgi kasutaja

    !!! Kui failist ei leia, siis tagastab null
    */
    public static User findUser(String username){
        File dir = new File(getDir(User.class));
        
        try{
            File[] files = dir.listFiles();

            for(File f : files){
                User user = mapper.readValue(f, User.class);
                if(user.getUsernameProperty().getValue().equals(username)) return user;
            }

            System.out.println("Kasutajat ei leitud: " + username);
            return null;

        } catch(IOException e){
            e.printStackTrace();
            System.out.println("Kasutajate kaust on tühi.");
            return null;
        }
    }

    /*
    Leiab kasutaja Taskgroupid.
    
    Kui failist ei leia, siis:
    - loob uue grupi (Igal kasutajal peab olema vähemalt üks taskgroup)
    - salvestab loodud TGmapperi
    */
    public static ArrayList<TaskGroup> findTaskgroups(UUID userid){
        ArrayList<TaskGroup> taskgroups = new ArrayList<>();
        try{
            UserTgMapper tgmap = Classreader.fromJsonFile(userid, UserTgMapper.class);

            for(UUID tgID : tgmap.getTaskgroups()){
                TaskGroup tg = Classreader.fromJsonFile(tgID, TaskGroup.class);
                taskgroups.add(tg);
            }

            return taskgroups;

        } catch(IOException e){ // Default task group: Tasks
            TaskGroup tg = new TaskGroup(userid);
            tg.setGroupname("Tasks");
            taskgroups.add(tg);
            
            UserTgMapper tgmapper = new UserTgMapper(userid, tg.getID());
            tgmapper.toJsonFile();

            return taskgroups;
        }
    }

    /*
    Loeb failist ID-le vastava objekti. Klass peab implementeerima ToJson liidest. 
    !! Kasutaja leidmiseks on olemas ka meetod: findUser(String username)
    */
    public static <T extends ToJson> T fromJsonFile(UUID id, Class<T> clazz) throws IOException{
        File file = new File(String.format("%s/%s.json", getDir(clazz), id));
        if (!file.isFile()) throw new FileNotFoundException("File not found: " + file);

        return mapper.readValue(file, clazz);
    }

    // Tagastab directory, kuhu failid salvestatakse
    public static <T extends ToJson> String getDir(Class<T> clazz){
        return String.format("%s/%s/", path, clazz.getSimpleName());
    }

    // Vajalik Jacksoni moodulile ToJson ja Classreader klassides 
    public static ObjectMapper getMapper(){
        return mapper;
    }


    public static <T extends ToJson> void deleteJsonFile(UUID id, Class<T> clazz) {
        File file = new File(String.format("%s/%s.json", getDir(clazz), id));
        try{
            if (!file.isFile()) {
                System.out.println("utils/classreader/deletejson() - File not found: " + file);
                return;
            }

            Files.delete(file.toPath());
            System.out.printf("File %s deleted.\n", file);

        }
        catch(IOException e){
            e.printStackTrace();
        }        
    }
}
