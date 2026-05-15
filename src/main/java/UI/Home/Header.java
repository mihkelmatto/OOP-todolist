package UI.Home;

import java.util.UUID;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Session;
import models.TaskGroup;

public class Header {
    private VBox layout;
    private Session session;

    public Header(Session session){
        this.session = session;
        VBox layout = new VBox();
        
        layout.getChildren().add(getNavbar());
        layout.getStyleClass().add("Header");

        this.layout = layout;
    }

    HBox getNavbar(){
        HBox navbar = new HBox();
        navbar.setSpacing(10);

        
        for(UUID tgid : this.session.getTaskgroups().keySet()){
            TaskGroup tg = this.session.getTaskgroups().get(tgid);

            Button navigation = new Button(tg.getGroupname());

            navbar.getChildren().add(navigation);
        }
        navbar.getStyleClass().add("Navbar");
        return navbar;
    }

    public VBox getLayout(){
        return this.layout;
    }
}
