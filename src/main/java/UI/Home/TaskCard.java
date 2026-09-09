package UI.Home;

import models.Task;
import models.TaskGroup;
import utils.widgets.EditableField;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TaskCard extends HBox{
    private ObjectProperty<TaskGroup> activeTGProperty;
    private Task task;
    private BooleanProperty editable;

    private Button complete;
    private EditableField title;
    private EditableField description;
    private DLwidget dlwidget;
    private Button options;    

    public TaskCard(Task task, ObjectProperty<TaskGroup> activeTGProperty){
        this.activeTGProperty = activeTGProperty;
        this.task = task;
        this.editable = task.getEditableProperty();

        this.complete = new Button();
        this.title = new EditableField(this.task.getTitleProperty().getValue());
        this.description = new EditableField(this.task.getDescriptionProperty().getValue());
        this.dlwidget = new DLwidget(this.task.getDeadlineProperty().getValue());
        this.options = new Button(); // ⋮

        initLayout();

        // events / listeners
        this.editable.addListener(e -> {
            setEditable(this.editable.getValue());
        });

        this.options.setOnAction(e -> {
            this.editable.setValue(!this.editable.getValue());
        });

        this.complete.setOnAction(e -> {
            TaskGroup activeTG = this.activeTGProperty.getValue();
            activeTG.removeTask(this.task);
        });

    }
    
    private void initLayout(){
        // layout
        this.setSpacing(25);
        HBox.setMargin(complete, new Insets(20, 10, 0, 0));
        HBox.setMargin(this.dlwidget, new Insets(10, 0, 10, 0));
        
        VBox contentarea = new VBox(this.title, this.description);
        HBox.setHgrow(contentarea, Priority.ALWAYS);
        
        VBox optionsbox = new VBox(this.options);
        
        this.getChildren().addAll(this.complete, contentarea, this.dlwidget, optionsbox);
        
        // css
        this.getStyleClass().add("TaskCard");

        this.complete.setId("Completebutton");

        contentarea.getStyleClass().add("contentarea");
        this.title.getStyleClass().add("title");
        this.description.getStyleClass().add("description");

        optionsbox.getStyleClass().add("Taskcard-optionsbox");
        
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Home/TaskCard.css").toExternalForm());
        
    }
    
    protected void setEditable(boolean editable){
        this.title.setEditable(editable);
        this.description.setEditable(editable);
        this.dlwidget.setEditable(editable);

        if(!editable){
            this.task.updateTitle(this.title.getValue());
            this.task.updateDescription(this.description.getValue());
            this.task.updateDeadline(this.dlwidget.getDateTime());

            FXCollections.sort(this.activeTGProperty.getValue().getTasksProperty());
        }
    }
}
