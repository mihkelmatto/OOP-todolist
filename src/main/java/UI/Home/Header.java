package UI.Home;

import java.time.LocalDateTime;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import models.Session;
import models.Task;
import models.TaskGroup;

public class Header {
    private HBox layout;
    private Session session;
    private SimpleObjectProperty<TaskGroup> activeTG;
    private TextField title;
    private ComboBox<TaskGroup> dropdowncontent;

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
        layout.setSpacing(10);

        // Title
        this.title = createTitle();
        HBox.setHgrow(title, Priority.ALWAYS);

        // New task
        Button newtask = new Button("New task");
        newtask.setOnAction(e -> {
            this.activeTG.getValue().addTask(new Task("New Task", "Description", LocalDateTime.of(2025, 1, 1, 0, 0)));
        });

        // Dropdown menu
        this.dropdowncontent = createDropdownContent();
        HBox dropdown = createDropdownWidget();


        // layout settings
        layout.getChildren().addAll(title, newtask, dropdown, createOptions());
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());
        layout.getStyleClass().add("Header");
        return layout;
    }

    private Button createOptions(){
        Button optionsbutton = new Button("⋮");
        ContextMenu options = new ContextMenu();

        options.setWidth(100);
        optionsbutton.setPrefWidth(30);

        MenuItem edit = new MenuItem("edit title");
        edit.setOnAction(e -> {
            editTitle();
        });
        
        MenuItem delete = new MenuItem("delete group");
        delete.setOnAction(e -> {
            this.session.deleteTaskgroup();
        });
        
        options.getItems().addAll(edit, delete);
        optionsbutton.setContextMenu(options);

        // TODO: joondus katki
        optionsbutton.setOnAction(e -> {
            int width = 50;
            options.setWidth(width);
            if(!options.isShowing()){
                options.show(optionsbutton, Side.BOTTOM, 0, 0);
                options.setX(optionsbutton.localToScreen(0, 0).getX() + optionsbutton.getWidth() - options.getWidth());
            }

        });

        options.setId("options");
        optionsbutton.setId("optionsbutton");
        return optionsbutton;
    }

    private TextField createTitle(){
        TextField title = new TextField();
        title.setEditable(false);
        title.setFocusTraversable(false);
        title.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                editTitle();
            }
        });
        title.setText(this.activeTG.getValue().getGroupnameProperty().getValue());
        title.getStyleClass().add("Header-title");

        return title;
    }

    private void editTitle(){
        if(this.title.isEditable()){
            this.title.setEditable(false);
            this.title.setFocusTraversable(false);
            this.activeTG.getValue().setGroupname(title.getText());
            this.title.getStyleClass().remove("Header-editable");
        }
        else{
            this.title.setEditable(true);
            this.title.setFocusTraversable(true);
            this.title.getStyleClass().add("Header-editable");
        }
    }

    private ComboBox<TaskGroup> createDropdownContent(){
        ComboBox<TaskGroup> content = new ComboBox<>(this.taskgroups);

        content.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
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

        content.setButtonCell(new javafx.scene.control.ListCell<>() {
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

        content.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.activeTG.setValue(newValue);
        });

        content.setValue(this.activeTG.getValue());

        return content;
    }

    private HBox createDropdownWidget(){
        Button button = new Button("+");
        button.setOnAction(e -> {
            this.session.createTaskgroup();
        });
        button.setId("dropdownbutton");

        HBox dropdown = new HBox();
        dropdown.getChildren().addAll(button, this.dropdowncontent);
        dropdown.setId("dropdownwidget");

        return dropdown;
    }

    public HBox getLayout(){
        return this.layout;
    }
}
