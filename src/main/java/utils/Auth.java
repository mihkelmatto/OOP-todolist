package utils;

import models.User;

public class Auth {
    // TODO: hashide võrdlemine
    public static User userauth(String username, String password){
        User user = Classreader.findUser(username);
        if(user == null) return null;
        else if(user.getPassword().equals(password)){
            return user;
        } else{
            return null;
        }
    }
     
    public static User createUser(String username, String password){
        if(username.length() > 0 && password.length() > 9){
            return new User(username, password);
        }
        else return null;
    }  
}
