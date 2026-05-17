package UI.Home;

import javafx.beans.property.SimpleStringProperty;
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

        // Title
        Label title = new Label();
        SimpleStringProperty activeTGtitle = new SimpleStringProperty();
        activeTGtitle.set(this.session.getActiveTGProperty().getValue().getGroupname());
        this.session.getActiveTGProperty().addListener(
            (obs, oldVal, newVal) -> {
                activeTGtitle.set(newVal.getGroupname());
            }
        );
        title.textProperty().bind(activeTGtitle);

        // spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Dropdown menu
        Button addTG = new Button("+");
        addTG.setOnAction(e -> {
            System.out.println("taskgroup added");
        });

        ComboBox<String> dropdown = new ComboBox<>();
        for(TaskGroup tg : this.session.getTaskgroups()){
            dropdown.getItems().add(tg.getGroupname());
        }
        dropdown.setValue(this.session.getActiveTGProperty().getValue().getGroupname());
        dropdown.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.session.setActiveTG(newValue);
        });      


        layout.getChildren().addAll(title, spacer, addTG, dropdown);
        layout.getStyleClass().add("Header");

        this.layout = layout;
        this.layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
    }

    public HBox getLayout(){
        return this.layout;
    }
}
