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

// TODO: mingi bug, kus headeri borderit on näha vaid siis kui header on aktiivne
// TODO: Dropdown-menüü CSS-i hover property ei tööta korrektselt

public class Header {
    private HBox layout;
    private Session session;

    public Header(Session session){
        this.session = session;
        HBox layout = new HBox();

        // Title
        Label title = new Label();
        SimpleStringProperty activeTGtitle = new SimpleStringProperty();
        activeTGtitle.set(this.session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());
        this.session.getActiveTGProperty().addListener(
            (obs, oldVal, newVal) -> {
                activeTGtitle.set(newVal.getGroupnameProperty().getValue());
            }
        );
        title.textProperty().bind(activeTGtitle);

        // spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Dropdown menu
        Button addTG = new Button("+");
        addTG.setOnAction(e -> {
            //todo: this.session.addTaskgroup();
        });

        ComboBox<String> dropdown = new ComboBox<>();
        for(TaskGroup tg : this.session.getTaskgroupProperty()){
            dropdown.getItems().add(tg.getGroupnameProperty().getValue());
        }
        dropdown.setValue(this.session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());
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
