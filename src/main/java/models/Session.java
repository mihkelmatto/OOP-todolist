package models;

import java.util.HashMap;

import io.github.robsonkades.uuidv7.UUIDv7;

/*
Sessioon peaks hakkama hoidma kõiki instantse, mida UI kasutab.

!! uurida javaFX bind meetodit
*/

public class Session {
    private User user;
    private HashMap<UUIDv7, TaskGroup> taskgroups;

    /*
    Testimise mõttes hetkel hard-coded.
    TODO: võimekus klasse failist lugeda
    */
    public Session(User user){
        this.user = user;
        
    }
}
