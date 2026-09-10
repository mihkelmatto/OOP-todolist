package UI.Home;

import models.Session;
import models.TaskGroup;
import utils.eventhandlers.TaskGroup.DeleteTGHandler;
import utils.eventhandlers.TaskGroup.EditTGHandler;
import utils.eventhandlers.TaskGroup.NewTGHandler;
import utils.events.SceneEvent.ChangeSceneEvent;
import utils.events.SceneEvent.SceneType;
import utils.events.TaskGroup.DeleteTGEvent;
import utils.events.TaskGroup.EditTGEvent;
import utils.events.TaskGroup.NewTGEvent;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Button;

public class HomeHeader extends utils.widgets.Header{
    private Session session;
    private ObjectProperty<TaskGroup> activeTGproperty;

    private DropdownWidget dropdownwidget; // TODO: DropdownWidgeti saaks nüüd teha lihtsalt comboboxiks?
    private Button account;
    
    public HomeHeader(Session session){
        super(session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());

        this.session = session;
        this.activeTGproperty = session.getActiveTGProperty();

        this.dropdownwidget = new DropdownWidget(this.session, this);
        this.account = new Button();

        initLayout();

        // events / listeners
        this.activeTGproperty.addListener(
            (obs, oldVal, newVal) -> {
                this.title.setValue(newVal.getGroupnameProperty().getValue());
            }
        );

        account.setOnAction(e -> {
            ChangeSceneEvent showaccount = new ChangeSceneEvent(SceneType.ACCOUNT);
            account.fireEvent(showaccount);
        }); 

        this.addEventHandler(
            NewTGEvent.NEW_TG,
            new NewTGHandler(this.session, this.getTitle(), this.dropdownwidget.getDropdown())
        );

        this.addEventHandler(
            EditTGEvent.EDIT_TG,
            new EditTGHandler(this.getTitle()));

        this.addEventHandler(
            DeleteTGEvent.DELETE_TG,
            new DeleteTGHandler(this.session, this.dropdownwidget.getDropdown()));
    }
    
    private void initLayout(){
        this.getChildren().addAll(this.dropdownwidget, this.account);
        
        // css
        this.account.setId("accbutton");
    }
}
