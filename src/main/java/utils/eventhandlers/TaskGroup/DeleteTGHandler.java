package utils.eventhandlers.TaskGroup;

import models.Session;
import models.TaskGroup;

import utils.events.TaskGroup.DeleteTGEvent;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;


public class DeleteTGHandler implements EventHandler<DeleteTGEvent>{
    private Session session;
    private ComboBox<TaskGroup> dropdown;

    public DeleteTGHandler(Session session, ComboBox<TaskGroup> dropdown){
        this.session = session;
        this.dropdown = dropdown;
    }

    @Override
    public void handle(DeleteTGEvent event){
        TaskGroup newactive = this.session.deleteTaskgroup();
        this.dropdown.getSelectionModel().select(newactive);
    }
    
}
