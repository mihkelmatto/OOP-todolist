package UI.Home;

import models.Task;
import utils.events.Task.DelTaskEvent;
import utils.events.Task.UpdateTaskEvent;
import utils.widgets.EditableField;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TaskCard extends HBox{
    private Task task;
    private boolean editable;

    private Button complete;
    private EditableField title;
    private EditableField description;
    private DLwidget dlwidget;
    private Button edit;    

    public TaskCard(Task task){
        this.task = task;
        this.editable = false;

        this.complete = new Button();
        this.title = new EditableField(this.task.getTitleProperty().getValue());
        this.description = new EditableField(this.task.getDescriptionProperty().getValue());
        this.dlwidget = new DLwidget(this.task.getDeadlineProperty().getValue());
        this.edit = new Button(); // ⋮

        initLayout();

        // events / listeners

        this.edit.setOnAction(e -> {
            setEditable(!editable);
        });

        this.complete.setOnAction(e -> {
            this.complete.fireEvent(new DelTaskEvent(this.task));
        });

        if(this.task.isnew()){
            setEditable(true);
            this.task.consumeNew();
        }
    }
    
    private void initLayout(){
        // layout
        this.setSpacing(25);
        HBox.setMargin(complete, new Insets(20, 10, 0, 0));
        HBox.setMargin(this.dlwidget, new Insets(10, 0, 10, 0));
        
        VBox contentarea = new VBox(this.title, this.description);
        HBox.setHgrow(contentarea, Priority.ALWAYS);
        
        VBox optionsbox = new VBox(this.edit);
        
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
    
    public void setEditable(boolean editable){
        this.editable = editable;
        this.title.setEditable(editable);
        this.description.setEditable(editable);
        this.dlwidget.setEditable(editable);

        if(!editable){
            this.edit.fireEvent(
                new UpdateTaskEvent(
                    task,
                    this.title.getValue(),
                    this.description.getValue(),
                    this.dlwidget.getDateTime()
                ));
        }
    }
}
