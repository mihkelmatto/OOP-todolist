package UI.Login;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import utils.events.AuthEvent.LoginEvent;
import utils.events.AuthEvent.RegisterEvent;

/*
    Navigatsioon TextFieldide vahel toimub enter-tabiga.

    Login ja Register nupud loovad vastava Eventi, mida haldab SceneManager.
*/

public class LoginBox extends VBox{
    private TextField username;
    private PasswordField password;

    private Button loginButton;
    private Button registerButton;
    
    public LoginBox(){
        this.username = new TextField();
        this.password = new PasswordField();

        this.loginButton = new Button("Login");
        this.registerButton = new Button("Register");

        initLayout();
        initEvents();
    }
    
    private void initLayout(){
        this.setSpacing(10);
        Label userlabel = new Label("Username");
        Label passlabel = new Label("Password");

        VBox buttonbox = new VBox(this.loginButton, this.registerButton);
        buttonbox.setSpacing(10);

        this.getChildren().addAll(userlabel, this.username, passlabel, this.password, buttonbox);

        // css
        this.getStyleClass().add("loginbox");
        buttonbox.setId("buttonbox");
        // need ID-d kasutusel ka scenemanageris eventide jaoks
        this.registerButton.setId("registerbutton");
        this.loginButton.setId("loginbutton");
    }

    private void initEvents(){
        this.username.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB) {
                this.password.requestFocus();
            }
        });
        
        this.password.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER){
                fireLoginEvent(this.loginButton);
            }
        });

        this.loginButton.setOnAction(e -> fireLoginEvent(this.loginButton));
        this.registerButton.setOnAction(e -> fireRegisterEvent(this.registerButton));
    }

    private void fireLoginEvent(Button button){
        LoginEvent loginevent = new LoginEvent(this.username.getText(), this.password.getText());
        button.fireEvent(loginevent);
    }

    private void fireRegisterEvent(Button button){
        RegisterEvent registerevent = new RegisterEvent(this.username.getText(), this.password.getText());
        button.fireEvent(registerevent); 
    }
}
