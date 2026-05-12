package models;

import java.util.HashMap;
import java.util.UUID;

/*
Sessioon peaks hakkama hoidma kõiki instantse, mida UI kasutab.

!! uurida javaFX bind meetodit
*/

public class Session {
    private User user;
    private HashMap<UUID, TaskGroup> taskgroups;

    public static void main(String[] args) {
    }

    public Session(UUID user){        
    }

}
