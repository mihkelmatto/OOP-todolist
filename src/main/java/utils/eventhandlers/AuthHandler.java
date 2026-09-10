package utils.eventhandlers;

import UI.SceneManager;

import models.Session;
import models.User;

import utils.Auth;
import utils.Classreader;

import utils.events.AuthEvent.AuthEvent;
import utils.events.AuthEvent.LoginEvent;
import utils.events.AuthEvent.LogoutEvent;
import utils.events.AuthEvent.RegisterEvent;

import javafx.event.EventHandler;

public class AuthHandler implements EventHandler<AuthEvent>{
    private final SceneManager scenemanager;

    public AuthHandler(SceneManager scenemanager){
        this.scenemanager = scenemanager;
    }

    @Override
    public void handle(AuthEvent event) {

        switch (event) {
            case LoginEvent e -> 
                login(e);

            case RegisterEvent e ->
                register(e);

            case LogoutEvent e -> 
                logout(e);
            
            default ->
                System.out.println("AuthHandler: invalid event type");
        }
    }
    
    private void startSession(User user){
        this.scenemanager.setSession(new Session(user));
        this.scenemanager.showHome();
    }

    private void login(LoginEvent event){
        User user;

        switch(event.getUsername()){
            case "" -> 
                user = Classreader.findUser("test");
            default ->
                user = Auth.userauth(event.getUsername(), event.getPassword());
        }

        if(user == null){
            System.out.println("AuthHandler: Login failed");
            return;
        }
        
        startSession(user);
    }

    private void register(RegisterEvent event){
        User user = Auth.createUser(event.getUsername(), event.getPassword());
        
        if(user == null){
            System.out.println("AuthHandler: Register failed");
        }

        startSession(user);
    }

    private void logout(LogoutEvent ignored){
        this.scenemanager.getSession().save();
        this.scenemanager.showLogin();
    }
}
