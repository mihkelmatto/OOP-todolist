package UI.LoginScene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import utils.LoginEvent;
import utils.RegisterEvent;

public class LoginBox {
    private VBox layout;
    private TextField username;
    private PasswordField password;

    public LoginBox(){
        this.layout = new VBox();
        this.layout.setSpacing(10);
        
        Label userlabel = new Label("Username");
        this.username = new TextField();

        Label passlabel = new Label("Password");
        this.password = new PasswordField();
        
        this.layout.getChildren().addAll(userlabel, this.username, passlabel, this.password, buttons2());
        this.layout.getStyleClass().add("loginbox");
    }

    private VBox buttons2(){
        VBox buttonbox = new VBox();
        buttonbox.setSpacing(10);

        Button login = new Button("Login");
        login.setOnAction(e -> {
            LoginEvent loginevent = new LoginEvent(this.username.getText(), this.password.getText());
            login.fireEvent(loginevent);                
        });

        Button register = new Button("Register");
        register.setOnAction(e -> {
            RegisterEvent registerevent = new RegisterEvent(this.username.getText(), this.password.getText());
            register.fireEvent(registerevent);
        });

        buttonbox.getChildren().addAll(login, register);
        buttonbox.setId("buttonbox");
        
        // need ID-d kasutusel ka scenemanageris eventide jaoks
        register.setId("registerbutton");
        login.setId("loginbutton");

        return buttonbox;
    }

    public VBox getLayout(){
        return this.layout;
    }
}
