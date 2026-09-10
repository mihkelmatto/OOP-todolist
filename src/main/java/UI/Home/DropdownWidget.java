package UI.Home;

import models.Session;
import models.Task;
import models.TaskGroup;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;

public class DropdownWidget extends HBox{
    private ObjectProperty<TaskGroup> activeTGProperty;
    private ObservableList<TaskGroup> taskgroups; 

    private Button newtask;
    private ComboBox<TaskGroup> dropdown;
    private OptionsButton options;

    public DropdownWidget(Session session, EventTarget target){
        this.activeTGProperty = session.getActiveTGProperty();
        this.taskgroups = session.getTaskgroupProperty();

        this.newtask = new Button("+");
        this.dropdown = createTGDropdown();
        this.options = new OptionsButton(target);

        initLayout();

        // events / listeners
        this.newtask.setOnAction(e -> {
            Task task = new Task();
            this.activeTGProperty.getValue().addTask(task); 
            task.getEditableProperty().setValue(true);     
        });
    }
    
    private void initLayout(){
        this.getChildren().addAll(this.newtask, this.dropdown, this.options);
        
        // css
        this.getStyleClass().add("dropdownsection");
        this.newtask.setId("newtaskbutton");

        this.getStylesheets().add(getClass().getResource("/Stylesheets/Home/DropdownSection.css").toExternalForm());
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

    public ComboBox<TaskGroup> getDropdown(){
        return this.dropdown;
    }
    
}
