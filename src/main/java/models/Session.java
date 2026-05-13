package models;

import utils.Classreader;

import java.util.HashMap;
import java.util.UUID;


/*
Sessioon peaks hakkama hoidma kõiki instantse, mida UI kasutab.

!! uurida javaFX bind meetodit
*/


/*
Igal kasutajal võiks tekkida loomise hetkel isiklik taskgroup, kuhu ta ülesandeid lisab
gruppe saab juurde teha ning grupi loomise ajal saab sinna liigutada vanu ülesandeid
Gruppi saab lisada teisi kasutajaid, mis juhul on kõik ülesanded jagatud
Kui UI avaneb, siis otsitakse, mis taskGroupis ta on ning edastatakse vastavad grupid

TODO: Userite nimekirja peaks ehk Setiks tegema, et ei peaks duplikaate kontrollima ning otsimisaeg oleks väiksem (Setil on vist O1)
*/

/*
Kui kasutaja kustutab taskgroupi:
1. iga task läheb default gruppi
2. iga kasutaja taskmapper lugeda ja uuendada.
3. enda taskmapperist vastav grupp kustutada
4. taskgroup kustutada

kui omanik eemaldab isiku taskgroupist:
1. kas on omanik?
2. vastava kasutaja taskmapper uuendada
3. taskgroupist eemaldada kasutaja UUID
*/

/*
Sessioon saab sisendiks kasutajanime

Kui ei leidu:
- luua uus User(String username)
- luua uus taskgroup(UUID owner) ning lisada taskgroupide nimekirja

Programmi sulgemisel:
Salvestada User
Salvestada Taskgroupid
Lugeda uus TGmapper
- kui ei leidu, siis teha uus
Salvestada uus TGmapper
*/
/*
Kuna User muudetakse?

*/
public class Session {
    private User user;
    private HashMap<UUID, TaskGroup> taskgroups;

    public static void main(String[] args) {
        Session session = new Session("Test");
        System.out.println(session);
        session.save();
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
