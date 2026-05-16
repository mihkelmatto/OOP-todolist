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
    private SimpleStringProperty activeTGtitle;

    public Header(Session session){
        this.session = session;
        this.activeTGtitle = session.getActiveTGProperty().getValue().getGroupnameProperty();

        HBox layout = new HBox();

        // Title
        Label title = new Label();
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
        dropdown.valueProperty().bind(activeTGtitle);
        dropdown.valueProperty().addListener((obs, oldValue, newValue) -> {

            // TODO: scrollable sisu muutmine
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
