package UI.Home;

import models.Session;
import models.TaskGroup;
import utils.events.ChangeSceneEvent;
import utils.events.SceneType;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Button;

public class HomeHeader extends utils.widgets.Header{
    private Session session;
    private ObjectProperty<TaskGroup> activeTG;

    private Button account;
    
    public HomeHeader(Session session){
        super(session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());

        this.session = session;
        this.activeTG = session.getActiveTGProperty();

        this.account = new Button();

        initLayout();

        // events / listeners
        this.activeTG.addListener(
            (obs, oldVal, newVal) -> {
                this.title.setValue(newVal.getGroupnameProperty().getValue());
            }
        );

        account.setOnAction(e -> {
            ChangeSceneEvent showaccount = new ChangeSceneEvent(SceneType.ACCOUNT);
            account.fireEvent(showaccount);
        }); 
    }
    
    private void initLayout(){
        this.getChildren().addAll(new DropdownWidget(this.session, this.title), this.account);
        
        // css
        this.account.setId("accbutton");
    }
}
