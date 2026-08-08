package UI.Login;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import utils.events.LoginEvent;
import utils.events.RegisterEvent;

public class LoginBox {
    private VBox layout;
    private TextField username;
    private PasswordField password;
    private Button loginButton;
    private Button registerButton;
    
    public LoginBox(){
        this.layout = new VBox();
        this.layout.setSpacing(10);
        
        Label userlabel = new Label("Username");
        this.username = new TextField();
        
        Label passlabel = new Label("Password");
        this.password = new PasswordField();
        
        this.username.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.TAB) {
                this.password.requestFocus();
            }
        });

        this.password.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                LoginEvent loginevent = new LoginEvent(this.username.getText(), this.password.getText());
                this.loginButton.fireEvent(loginevent);  
            }
        });

        this.layout.getChildren().addAll(userlabel, this.username, passlabel, this.password, buttons2());
        this.layout.getStyleClass().add("loginbox");
    }

    private VBox buttons2(){
        VBox buttonbox = new VBox();
        buttonbox.setSpacing(10);

        this.loginButton = new Button("Login");
        this.loginButton.setOnAction(e -> {
            LoginEvent loginevent = new LoginEvent(this.username.getText(), this.password.getText());
            this.loginButton.fireEvent(loginevent);                
        });

        this.registerButton = new Button("Register");
        this.registerButton.setOnAction(e -> {
            RegisterEvent registerevent = new RegisterEvent(this.username.getText(), this.password.getText());
            this.registerButton.fireEvent(registerevent);
        });

        buttonbox.getChildren().addAll(this.loginButton, this.registerButton);
        buttonbox.setId("buttonbox");
        
        // need ID-d kasutusel ka scenemanageris eventide jaoks
        this.registerButton.setId("registerbutton");
        this.loginButton.setId("loginbutton");

        return buttonbox;
    }

    public VBox getLayout(){
        return this.layout;
    }
}
