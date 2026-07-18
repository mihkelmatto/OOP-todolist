package utils;

import com.password4j.Password;

import models.User;

public class Auth {
    public static User userauth(String username, String password){
        User user = Classreader.findUser(username);
        if(user == null) return null;
        else if(Password.check(password, user.getPassword()).withArgon2()){
            return user;
        } else{
            System.out.println("Auth: password check failed");
            return null;
        }
    }
     
    public static User createUser(String username, String password){
        User user = Classreader.findUser(username);
        if(user != null){
            System.out.printf("User %s already exists\n", username);
            return null;            
        } else if(username.length() > 0 && password.length() > 9){
            return new User(username, Password.hash(password).withArgon2().getResult());
        }
        else return null;
    }  
}
