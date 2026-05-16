package UI.Home;

import java.util.UUID;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import models.Session;
import models.TaskGroup;

public class Header {
    private HBox layout;
    private Session session;

    public Header(Session session){
        this.session = session;
        HBox layout = new HBox();
        
        Label activeTG = new Label("My tasks");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Taskgroup menüü
        Button addTG = new Button("+");
        addTG.setOnAction(e -> {
            System.out.println("taskgroup added");
        });

        ComboBox<String> dropdown = new ComboBox<>();
        for(UUID tgid : this.session.getTaskgroups().keySet()){
            TaskGroup tg = this.session.getTaskgroups().get(tgid);
            dropdown.getItems().add(tg.getGroupname());
        }
        dropdown.setValue(this.session.getTaskgroups().values().iterator().next().getGroupname()); // TODO: kuidas saaks default-value täpsemalt määrata?

        layout.getChildren().addAll(activeTG, spacer, addTG, dropdown);
        layout.getStyleClass().add("Header");

        this.layout = layout;
        this.layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
    }

    public HBox getLayout(){
        return this.layout;
    }
}
