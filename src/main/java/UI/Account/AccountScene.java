package UI.Account;

import models.User;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class AccountScene extends Scene{

    public AccountScene (User user){
        super(createRoot(user));

        this.getStylesheets().add(getClass().getResource("/Stylesheets/Global.css").toExternalForm());
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Account/AccountScene.css").toExternalForm());
    }

    private static VBox createRoot(User user){
        VBox root = new VBox();

        ScrollPane midsection = new AccountBody(user);
        VBox.setVgrow(midsection, Priority.ALWAYS);

        root.getChildren().addAll(new AccountHeader(), midsection);
        
        return root;
    }
}
