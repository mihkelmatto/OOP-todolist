package UI.Home;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;

import models.Session;
import models.TaskGroup;

import utils.events.ChangeSceneEvent;
import utils.events.SceneType;
import utils.widgets.EditableField;

public class Header extends utils.widgets.Header{
    private Session session;
    private SimpleObjectProperty<TaskGroup> activeTG;
    
    private EditableField title;

    public Header(Session session){
        super(session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());
        this.title = this.getTitleField();
        this.session = session;
        this.activeTG = session.getActiveTGProperty();

        this.activeTG.addListener(
            (obs, oldVal, newVal) -> {
                this.title.setValue(newVal.getGroupnameProperty().getValue());
            }
        );
        
        this.getChildren().addAll(new DropdownSection(this.session, this.title), createAccbutton());
    }

    private Button createAccbutton(){
        Button account = new Button();
        account.setOnAction(e -> {
            ChangeSceneEvent showaccount = new ChangeSceneEvent(SceneType.ACCOUNT);
            account.fireEvent(showaccount);
        });
        account.setId("accbutton");
        return account;
    }
}
