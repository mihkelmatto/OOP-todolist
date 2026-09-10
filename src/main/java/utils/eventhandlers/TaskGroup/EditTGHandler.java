package utils.eventhandlers.TaskGroup;

import javafx.event.EventHandler;
import utils.events.TaskGroup.EditTGEvent;
import utils.widgets.EditableField;

public class EditTGHandler implements EventHandler<EditTGEvent>{
    private EditableField headertitle;

    public EditTGHandler(EditableField headertitle){
        this.headertitle = headertitle;
    }

    @Override
    public void handle(EditTGEvent event){
        this.headertitle.setEditable(true);
    }
}
