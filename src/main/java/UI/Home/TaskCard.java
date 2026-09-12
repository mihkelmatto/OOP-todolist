package UI.Home;

import models.Task;
import utils.eventhandlers.OutsideClickHandler;
import utils.events.Task.DelTaskEvent;
import utils.events.Task.UpdateTaskEvent;
import utils.widgets.EditableField;
import utils.widgets.svg.SVGButton;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class TaskCard extends HBox{
    private Task task;
    private boolean editable;
    private final OutsideClickHandler clickhandler;

    private Button complete;
    private EditableField title;
    private EditableField description;
    private DLwidget dlwidget;
    private Button edit;

    public TaskCard(Task task){
        this.task = task;
        this.editable = false;
        this.clickhandler = new OutsideClickHandler(this);

        this.complete = new Button();
        this.title = new EditableField(this.task.getTitleProperty().getValue());
        this.description = new EditableField(this.task.getDescriptionProperty().getValue());
        this.dlwidget = new DLwidget(this.task.getDeadlineProperty().getValue());
        this.edit = new SVGButton("editicon.path"); // ⋮

        initLayout();

        
        // events / listeners
        this.edit.setOnAction(e -> {
            setEditable(!editable);
        });
        
        this.complete.setOnAction(e -> {
            this.complete.fireEvent(new DelTaskEvent(this.task));
        });
        
        this.clickhandler.setAction(() -> setEditable(false));
        this.clickhandler.setTarget(this);

        /*
            Kui task on uus, siis kutsutakse HomeBody ListCellis setEditable(true)
            Seda ei saa konstruktoris teha, sest muidu on TaskCardi Scene null.
            (vt. this.setclickhandler())
        */
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
        if(this.editable == editable){
            return;
        }

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

        setClickFilter(editable);
    }


    public void setClickFilter(boolean active){
        if(active){
            getScene().addEventFilter(
                MouseEvent.MOUSE_PRESSED,
                clickhandler
            );
        }
        else{
            getScene().removeEventFilter(
                MouseEvent.MOUSE_PRESSED,
                clickhandler
            );
        }
    }

    /*
        Eemaldab vanemklassidest viited
        !! Tuleb alati kutsuda enne TaskCardi eemaldamist (lifecycle)
    */
    public void dispose(){
        this.setClickFilter(false);
    }
}
