package utils.eventhandlers.TaskGroup;

import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import models.Session;
import models.TaskGroup;
import utils.events.TaskGroup.NewTGEvent;
import utils.widgets.EditableField;

public class NewTGHandler implements EventHandler<NewTGEvent>{
    private Session session;
    private EditableField headertitle;
    private ComboBox<TaskGroup> dropdown;

    public NewTGHandler(Session session, EditableField headertitle, ComboBox<TaskGroup> dropdown){
        this.session = session;
        this.headertitle = headertitle;
        this.dropdown = dropdown;
    }

    @Override
    public void handle(NewTGEvent event){
        TaskGroup newgroup = this.session.createTaskgroup();
        this.headertitle.setEditable(true);
        this.dropdown.getSelectionModel().select(newgroup);
    }
}
