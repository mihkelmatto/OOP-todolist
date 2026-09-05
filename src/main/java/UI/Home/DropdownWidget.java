package UI.Home;

import utils.widgets.EditableField;

import models.Session;
import models.Task;
import models.TaskGroup;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;

public class DropdownWidget extends HBox{
    private Session session;
    private EditableField headerTitle;

    private ObjectProperty<TaskGroup> activeTGProperty;
    private ObservableList<TaskGroup> taskgroups; 

    private Button newtask;
    private ComboBox<TaskGroup> dropdown;
    private Button options;
    private Popup optionsPopup;

    public DropdownWidget(Session session, EditableField headerTitle){
        this.session = session; // TODO: create/deletetaskgroup eventina?
        this.headerTitle = headerTitle;

        this.activeTGProperty = session.getActiveTGProperty();
        this.taskgroups = session.getTaskgroupProperty();

        this.newtask = new Button("+");
        this.dropdown = createTGDropdown();
        this.options = new Button("⋮");
        this.optionsPopup = new Popup(); 

        initLayout();

        // events / listeners
        this.newtask.setOnAction(e -> {
            Task task = new Task();
            this.activeTGProperty.getValue().addTask(task); 
            task.getEditableProperty().setValue(true);     
        });

        this.options.setOnAction(e -> {
            if(this.optionsPopup.isShowing()){
                this.optionsPopup.hide();
                return;
            }
            this.optionsPopup.show(this.options, 0, 0);
            this.optionsPopup.setX(this.options.localToScreen(0, this.options.getHeight()).getX() + this.options.getWidth() - this.optionsPopup.getWidth());
            this.optionsPopup.setY(this.options.localToScreen(0, this.options.getHeight()).getY());
        });

    }
    
    private void initLayout(){
        this.optionsPopup.getContent().add(createOptionsContent());
        this.optionsPopup.setAutoHide(true);
        
        
        this.getChildren().addAll(this.newtask, this.dropdown, this.options);
        
        // css
        this.getStyleClass().add("dropdownsection");
        this.newtask.setId("newtaskbutton");
        this.options.setId("optionsbutton");

        this.getStylesheets().add(getClass().getResource("/Stylesheets/Home/DropdownSection.css").toExternalForm());
    }

    private VBox createOptionsContent(){
        VBox vbox = new VBox();

        Button add = new Button("new group");
        add.setOnAction(e -> {
            TaskGroup newgroup = this.session.createTaskgroup();
            this.headerTitle.setEditable(true);
            this.dropdown.getSelectionModel().select(newgroup);
            this.optionsPopup.hide();
        });
        
        Button edit = new Button("edit title");
        edit.setOnAction(e -> {
            this.headerTitle.setEditable(true);
            this.optionsPopup.hide();
        });

        Button delete = new Button("delete group");
        delete.setOnAction(e -> {
            TaskGroup newactive = this.session.deleteTaskgroup();
            this.dropdown.getSelectionModel().select(newactive);
            this.optionsPopup.hide();
        });

        vbox.getChildren().addAll(add, edit, delete);
        vbox.getStyleClass().add("optionscontent");
        
        vbox.getStylesheets().add(getClass().getResource("/Stylesheets/Widgets/Header.css").toExternalForm());
        vbox.getStylesheets().add(getClass().getResource("/Stylesheets/Home/DropdownSection.css").toExternalForm());

        return vbox;
    }    

    private ComboBox<TaskGroup> createTGDropdown(){
        ComboBox<TaskGroup> dropdown = new ComboBox<>(this.taskgroups);
        
        dropdown.setCellFactory(lv -> createTGCell());
        dropdown.setButtonCell(createTGCell());
        
        dropdown.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.activeTGProperty.setValue(newValue);
        });
        
        dropdown.setValue(this.activeTGProperty.getValue());

        return dropdown;
    }

    private ListCell<TaskGroup> createTGCell() {
        return new ListCell<>() {
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
        };
    }
    
}
