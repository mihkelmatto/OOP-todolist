package models;

import java.util.HashMap;

import io.github.robsonkades.uuidv7.UUIDv7;

public class Session {
    private User user;
    private HashMap<UUIDv7, TaskGroup> taskgroups;

    /*
    Testimise mõttes hetkel hard-coded.
    TODO: võimekus klasse failist lugeda
    */
    public Session(){
        this.user = new User("Peeter");
        
    }
}
