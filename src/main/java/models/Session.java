package models;

import utils.Classreader;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

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
    private HashMap<UUID, TaskGroup> taskgroups;

    public static void main(String[] args) throws Exception {
        Session session = new Session("Mari");

        UUID tgid = session.taskgroups.keySet().iterator().next();
        TaskGroup tg = session.taskgroups.get(tgid);
        tg.addTasks(createSampleTasks());
        
        session.taskgroups.put(tgid, tg);
        
        System.out.println(session);
        session.save();
    }

    public static Task[] createSampleTasks() {
        return new Task[] {
            new Task("Projektiplaan", "Koosta algne projektiplaan ja eesmärgid", LocalDateTime.now().plusDays(2)),
            new Task("Andmeanalüüs", "Analüüsi esialgsed andmekogumid", LocalDateTime.now().plusDays(4)),
            new Task("Kasutajaliides", "Disaini rakenduse UI prototüüp", LocalDateTime.now().plusDays(6)),
            new Task("Testimine", "Koosta testjuhtumid põhifunktsioonidele", LocalDateTime.now().plusDays(8)),
            new Task("Dokumentatsioon", "Kirjuta tehniline dokumentatsioon", LocalDateTime.now().plusDays(10)),
            new Task("Integratsioon", "Ühenda teenused ja API-d", LocalDateTime.now().plusDays(12)),
            new Task("Koodiarvustus", "Teosta koodikvaliteedi ülevaatus", LocalDateTime.now().plusDays(14))
        };
    }

    public Session(String username){
        this.user = Classreader.findUser(username);
        this.taskgroups = Classreader.findTaskgroups(this.user.getID());
    }

    // salvestamise ajal vist ei pea tgmapperit kontrollima?
    public void save(){
        this.user.toJsonFile();
        for(UUID tgid : taskgroups.keySet()){
            taskgroups.get(tgid).toJsonFile();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Session info: \n");
        sb.append(user.toString());
        for(UUID tgid : taskgroups.keySet()) sb.append(taskgroups.get(tgid).toString() + "\n");
        return sb.toString();
    }
}
