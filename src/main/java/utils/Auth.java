package utils;

import models.User;

public class Auth {
    public static User userauth(String password){
        User user = Classreader.findUser("Mari");

        // TODO

        return user;
    }
}
