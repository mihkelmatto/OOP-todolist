package utils.eventhandlers;


import javafx.event.EventHandler;
import models.Session;
import models.TaskGroup;
import utils.events.TaskGroup.DelTGEvent;
import utils.events.TaskGroup.EditTGEvent;
import utils.events.TaskGroup.NewTGEvent;
import utils.events.TaskGroup.TGevent;
import utils.widgets.EditableField;

public class TGHandler implements EventHandler<TGevent>{
    private final Session session;
    private final EditableField headertitle;

    public TGHandler(Session session, EditableField headertitle){
        this.session = session;
        this.headertitle = headertitle;
    }

    @Override
    public void handle(TGevent event) {

        switch (event) {
            case NewTGEvent e -> 
                newTG();

            case EditTGEvent e ->
                EditTG();

            case DelTGEvent e -> 
                DelTG();
            
            default ->
                System.out.println("TGHandler: invalid event type");
        }
    }

    private void newTG(){
        TaskGroup newgroup = this.session.createTaskgroup();
        newgroup.toJsonFile();

        this.headertitle.setEditable(true);
    }

    private void EditTG(){
        this.headertitle.setEditable(true);
    }

    private void DelTG(){
        this.session.deleteTaskgroup();
    }
    
}
