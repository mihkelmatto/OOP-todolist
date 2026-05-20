package UI.Home;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import models.Session;
import models.Task;
import models.TaskGroup;

public class Header {
    private HBox layout;
    private Session session;
    private SimpleObjectProperty<TaskGroup> activeTG;
    private TextField title;
    private ComboBox<TaskGroup> dropdown;

    private ObservableList<TaskGroup> taskgroups;

    public Header(Session session){
        this.session = session;
        
        this.activeTG = this.session.getActiveTGProperty();

        this.taskgroups = this.session.getTaskgroupProperty();

        this.activeTG.addListener(
            (obs, oldVal, newVal) -> {
                this.title.setText(newVal.getGroupnameProperty().getValue());
            }
        );

        this.layout = createLayout();
    }

    public HBox createLayout(){
        HBox layout = new HBox();

        // Title
        this.title = new TextField();
        this.title.setEditable(false);
        this.title.setText(this.activeTG.getValue().getGroupnameProperty().getValue());
        this.title.getStyleClass().add("Header-title");

        Button edittitle = new Button();
        edittitle.setOnAction(e -> {
            if(title.isEditable()){
                title.setEditable(false);
                this.activeTG.getValue().setGroupname(title.getText());
                title.getStyleClass().remove("Header-editable");
            }
            else{
                title.setEditable(true);
                title.getStyleClass().add("Header-editable");
            }
        });
        edittitle.getStyleClass().add("Header-edittitle");

        // new task
        Button newtask = new Button("New task");
        newtask.setOnAction(e -> {
            this.activeTG.getValue().addTask(new Task("New Task", "Description", LocalDateTime.of(2025, 1, 1, 0, 0)));
        });
        newtask.getStyleClass().add("Header-newtask");

        // spacer
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        // Dropdown menu
        Button dropdownbutton = new Button("+");
        dropdownbutton.setOnAction(e -> {
            this.session.createTaskgroup();
        });
        dropdownbutton.getStyleClass().add("Header-dropdownbutton");

        this.dropdown = createDropdown();


        // layout settings
        layout.getChildren().addAll(edittitle, title, newtask, spacer, dropdownbutton, dropdown);
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
        layout.getStyleClass().add("Header");
        return layout;
    }

    private ComboBox<TaskGroup> createDropdown(){
        ComboBox<TaskGroup> dropdown = new ComboBox<>(this.taskgroups);

        dropdown.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(TaskGroup tg, boolean empty) {
                super.updateItem(tg, empty);
                textProperty().unbind();

                if (empty || tg == null) {
                    setText(null);
                } else {
                    textProperty().bind(tg.getGroupnameProperty());
                }
            }
        });

        dropdown.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(TaskGroup tg, boolean empty) {
                super.updateItem(tg, empty);
                textProperty().unbind();

                if (empty || tg == null) {
                    setText(null);
                } else {
                    textProperty().bind(tg.getGroupnameProperty());
                }
            }
        });

        dropdown.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.activeTG.setValue(newValue);
        });

        dropdown.setValue(this.activeTG.getValue());

        return dropdown;
    }

    public HBox getLayout(){
        return this.layout;
    }
}
