package UI.LoginScene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
            String input = username.getText();
            System.out.printf("Username input: %s\n", input);
        });

        Button register = new Button("Register");
        register.setOnAction(e -> {
            String input = password.getText();
            System.out.printf("Password input: %s\n", input);
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
