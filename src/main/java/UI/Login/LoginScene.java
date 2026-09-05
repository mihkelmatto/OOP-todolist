package UI.Login;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
    LoginBox asub LoginScene keskel kahe layout manageri vahel
*/

public class LoginScene extends Scene{
    public LoginScene(){
        super(createRoot());

        // css
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Global.css").toExternalForm());
        this.getStylesheets().add(getClass().getResource("/Stylesheets/LoginScene.css").toExternalForm());
    }

    private static HBox createRoot(){
        HBox root = new HBox();
        root.setAlignment(Pos.CENTER);

        VBox centercol = new VBox(new LoginBox());
        centercol.setAlignment(Pos.CENTER);

        root.getChildren().addAll(centercol);

        return root;
    }
}
