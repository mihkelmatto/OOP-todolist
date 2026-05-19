package UI.Home;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.SortedList;
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
    private SimpleObjectProperty<TaskGroup> activeTG;
    private SimpleStringProperty activeTGtitle;

    private SortedList<TaskGroup> taskgroups;

    public Header(Session session){
        this.session = session;
        
        this.activeTG = this.session.getActiveTGProperty();
        this.activeTGtitle = new SimpleStringProperty();
        this.activeTGtitle.set(this.session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());

        this.taskgroups = this.session.getTaskgroupProperty();
        this.taskgroups.addListener((ListChangeListener<TaskGroup>) change -> {

        });

        this.activeTG.addListener(
            (obs, oldVal, newVal) -> {
                this.activeTGtitle.set(newVal.getGroupnameProperty().getValue());
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

        ComboBox<TaskGroup> dropdown = new ComboBox<>(this.taskgroups);
        // Converter õpetab ComboBoxile, kuidas teisendada oma objektide ja pealkirjade vahel
        dropdown.setConverter(new javafx.util.StringConverter<>() {
            @Override
            public String toString(TaskGroup tg) {
                return tg.getGroupnameProperty().getValue() == null ? "" : tg.getGroupnameProperty().getValue();
            }

            @Override
            public TaskGroup fromString(String groupname) {
                for(TaskGroup tg : taskgroups){
                    if(tg.getGroupnameProperty().getValue().equals(groupname)) return tg;
                }
                
                return new TaskGroup(session.getUser().getID());
            }
        });
        // tegevused dropdownist eseme valimisel
        dropdown.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.activeTG.setValue(newValue);
        });
        dropdown.setValue(this.activeTG.getValue());


        // layout settings
        layout.getChildren().addAll(title, spacer, dropdownbutton, dropdown);
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
        layout.getStyleClass().add("Header");
        return layout;
    }

    public HBox getLayout(){
        return this.layout;
    }
}
