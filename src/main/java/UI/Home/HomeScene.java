package UI.Home;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import models.Session;

public class HomeScene{
    private Session session;
    private Scene scene;

    public HomeScene(Session session){
        this.session = session;        
        VBox root = new VBox();

        Header header = new Header(this.session);
        MidSection midsection = new MidSection(this.session);

        root.getChildren().addAll(header.getLayout(), midsection.getLayout());

        this.scene = new Scene(root);
        this.scene.getStylesheets().add(getClass().getResource("/Stylesheets/HomeScene.css").toExternalForm());
    }

    public Scene getScene(){
        return this.scene;
    }
}
