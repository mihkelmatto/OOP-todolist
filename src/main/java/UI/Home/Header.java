package UI.Home;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.css.converter.StringConverter;
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
// TODO: Dropdown-menüü vahetamisel kaob selected item ära
public class Header {
    private HBox layout;
    private Session session;
    private SimpleStringProperty activeTGtitle;
    private ObservableList<SimpleStringProperty> taskgroupnames;

    public Header(Session session){
        this.session = session;

        this.activeTGtitle = new SimpleStringProperty();
        this.activeTGtitle.set(this.session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());
        
        this.taskgroupnames = FXCollections.observableArrayList();
        refreshTaskgroupnames();
        // Session listeners
        this.session.getTaskgroupProperty().addListener((ListChangeListener<TaskGroup>) change -> {
            refreshTaskgroupnames();
        });

        this.session.getActiveTGProperty().addListener(
            (obs, oldVal, newVal) -> {
                this.activeTGtitle.set(newVal.getGroupnameProperty().getValue());
                refreshTaskgroupnames();
            }
        );

        this.layout = createLayout();
    }

    public HBox createLayout(){
        HBox layout = new HBox();

        // Title
        Label title = new Label();
        title.textProperty().bind(activeTGtitle);

        // spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Dropdown menu
        Button dropdownbutton = new Button("+");
        dropdownbutton.setOnAction(e -> {
            this.session.createTaskgroup();
        });

        ComboBox<SimpleStringProperty> dropdown = new ComboBox<>(this.taskgroupnames);
        dropdown.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(SimpleStringProperty value) {
                return value == null ? "" : value.get();
            }

            @Override
            public SimpleStringProperty fromString(String string) {
                return new SimpleStringProperty(string);
            }
        });

        dropdown.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.session.setActiveTG(newValue.getValue());
        });
        dropdown.setValue(this.session.getActiveTGProperty().getValue().getGroupnameProperty());


        // layout settings
        layout.getChildren().addAll(title, spacer, dropdownbutton, dropdown);
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
        layout.getStyleClass().add("Header");
        return layout;
    }

    public void refreshTaskgroupnames(){
        this.taskgroupnames.clear();
        for(TaskGroup tg : this.session.getTaskgroupProperty()){
            this.taskgroupnames.add(tg.getGroupnameProperty());
        }
    }

    public HBox getLayout(){
        return this.layout;
    }
}
