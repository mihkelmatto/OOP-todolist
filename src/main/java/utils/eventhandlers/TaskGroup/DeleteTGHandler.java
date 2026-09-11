package utils.eventhandlers.TaskGroup;

import models.Session;
import utils.events.TaskGroup.DeleteTGEvent;

import javafx.event.EventHandler;


public class DeleteTGHandler implements EventHandler<DeleteTGEvent>{
    private Session session;

    public DeleteTGHandler(Session session){
        this.session = session;
    }

    @Override
    public void handle(DeleteTGEvent event){
        this.session.deleteTaskgroup();
    }
    
}
