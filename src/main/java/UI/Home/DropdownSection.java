package UI.Home;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

import models.Session;
import models.Task;
import models.TaskGroup;

import utils.widgets.EditableField;

public class DropdownSection extends HBox{
    private Session session;
    private EditableField headerTitle;

    private SimpleObjectProperty<TaskGroup> activeTG;
    private ObservableList<TaskGroup> taskgroups; 
    private ComboBox<TaskGroup> dropdown;

    public DropdownSection(Session session, EditableField headerTitle){
        this.session = session;
        this.headerTitle = headerTitle;

        this.activeTG = session.getActiveTGProperty();
        this.taskgroups = session.getTaskgroupProperty();
        this.dropdown = createDropdown();

        this.getChildren().addAll(createNewtaskbutton(), createDropdown(), createGroupOptions());
        this.getStyleClass().add("dropdownsection");
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Home/DropdownSection.css").toExternalForm());
    }

    private Button createNewtaskbutton(){
        Button newtask = new Button("+");

        newtask.setOnAction(e -> {
            Task task = new Task();
            this.session.getActiveTGProperty().getValue().addTask(task); 
            task.getEditableProperty().setValue(true);     
        });
        newtask.setId("newtaskbutton");

        return newtask;
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
    
    private Button createGroupOptions(){
        Button optionsbutton = new Button("⋮");
        Popup popup = new Popup();

        VBox vbox = new VBox();

        Button add = new Button("new group");
        add.setOnAction(e -> {
            TaskGroup newgroup = this.session.createTaskgroup();
            this.headerTitle.setEditable(true);
            this.dropdown.getSelectionModel().select(newgroup);
            popup.hide();
        });
        
        Button edit = new Button("edit title");
        edit.setOnAction(e -> {
            this.headerTitle.setEditable(true);
            popup.hide();
        });

        Button delete = new Button("delete group");
        delete.setOnAction(e -> {
            TaskGroup newactive = this.session.deleteTaskgroup();
            this.dropdown.getSelectionModel().select(newactive);
            popup.hide();
        });

        vbox.getChildren().addAll(add, edit, delete);
        popup.getContent().add(vbox);
        
        optionsbutton.setOnAction(e -> {
            if(popup.isShowing()){
                popup.hide();
                return;
            }
            popup.show(optionsbutton, 0, 0);
            popup.setX(optionsbutton.localToScreen(0, optionsbutton.getHeight()).getX() + optionsbutton.getWidth() - popup.getWidth());
            popup.setY(optionsbutton.localToScreen(0, optionsbutton.getHeight()).getY());
        });

        vbox.getStyleClass().add("optionsmenu");
        optionsbutton.setId("optionsbutton");
        popup.setAutoHide(true);

        vbox.getStylesheets().add(
            getClass().getResource("/Stylesheets/Widgets/Header.css").toExternalForm()
        );

        vbox.getStylesheets().add(getClass().getResource("/Stylesheets/Home/DropdownSection.css").toExternalForm());

        return optionsbutton;
    }    
}
