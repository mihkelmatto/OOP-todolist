package UI.Home;

import models.Session;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class HomeScene extends Scene{

    public HomeScene(Session session){
        super(createRoot(session));
        
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Global.css").toExternalForm());
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Home/HomeScene.css").toExternalForm());
    }
    
    private static VBox createRoot(Session session){
        VBox root = new VBox();

        HomeHeader header = new HomeHeader(session);
        HomeBody midsection = new HomeBody(session.getActiveTGProperty());
    
        root.getChildren().addAll(header, midsection);

        return root;
    }
}
