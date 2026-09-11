package UI.Home;

import models.Task;
import models.TaskGroup;
import utils.eventhandlers.Task.DelTaskHandler;
import utils.events.Task.DelTaskEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HomeBody extends ListView<Task>{
    private ObjectProperty<TaskGroup> activeTGproperty;

    public HomeBody(ObjectProperty<TaskGroup> activeTGproperty){
        this.activeTGproperty = activeTGproperty;

        initLayout();
        initCellFactory();
        
        // events / listeners
        this.activeTGproperty.addListener(e -> {
            this.setItems(this.activeTGproperty.getValue().getTasksProperty());
        });

        this.addEventHandler(
            DelTaskEvent.DEL_TASK,
            new DelTaskHandler(activeTGproperty));
    }
    
    private void initLayout(){
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setItems(this.activeTGproperty.getValue().getTasksProperty());
        
        // css
        this.getStyleClass().add("MidSection");
    }

    private void initCellFactory(){
        this.setCellFactory(param -> new ListCell<Task>() {

            @Override
            protected void updateItem(Task task, boolean empty) {

                super.updateItem(task, empty);

                if(empty || task == null){
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new TaskCard(task));
                }
            }
        });
    }
}
