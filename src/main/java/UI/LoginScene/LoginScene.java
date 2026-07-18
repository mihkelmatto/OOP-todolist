package UI.LoginScene;

import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class LoginScene{
    private Scene scene;

    public LoginScene(){
        HBox root = new HBox();

        VBox centercol = new VBox();

        centercol.getChildren().addAll(new LoginBox().getLayout());
        centercol.getStyleClass().add("centercol");
        
        root.getChildren().addAll(centercol);

        this.scene = new Scene(root);
        this.scene.getStylesheets().add(getClass().getResource("/Stylesheets/Global.css").toExternalForm());
        this.scene.getStylesheets().add(getClass().getResource("/Stylesheets/LoginScene.css").toExternalForm());
    }

    public Scene getScene(){
        return this.scene;
    }
}
