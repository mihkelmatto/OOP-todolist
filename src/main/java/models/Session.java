package models;

import java.util.HashMap;
import java.util.UUID;

import utils.ToJson;

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
public class Session {
    private User user;
    private HashMap<UUID, TaskGroup> taskgroups;
    private UserTgMapper tgmap;

    public static void main(String[] args) {
    }

    public Session(UUID userid){     

    }
}
