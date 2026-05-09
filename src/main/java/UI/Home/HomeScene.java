package UI.Home;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class HomeScene{
    
    private Scene scene;

    public HomeScene(){
        VBox root = new VBox();

        Header header = new Header();
        MidSection midsection = new MidSection();

        root.getChildren().addAll(header.getLayout(), midsection.getLayout());

        this.scene = new Scene(root);
        this.scene.getStylesheets().add(getClass().getResource("/Stylesheets/Global.css").toExternalForm());
    }

    public Scene getScene(){
        return this.scene;
    }
}
