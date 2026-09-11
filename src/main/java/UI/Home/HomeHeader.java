package UI.Home;

import models.Session;
import models.TaskGroup;

import utils.eventhandlers.Task.NewTaskHandler;
import utils.eventhandlers.TaskGroup.DeleteTGHandler;
import utils.eventhandlers.TaskGroup.EditTGHandler;
import utils.eventhandlers.TaskGroup.NewTGHandler;
import utils.events.SceneEvent.ChangeSceneEvent;
import utils.events.SceneEvent.SceneType;
import utils.events.Task.NewTaskEvent;
import utils.events.TaskGroup.DeleteTGEvent;
import utils.events.TaskGroup.EditTGEvent;
import utils.events.TaskGroup.NewTGEvent;

import javafx.beans.property.ObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class HomeHeader extends utils.widgets.Header{
    private final Session session;
    private final ObjectProperty<TaskGroup> activeTGproperty;

    private final Button newtask;
    private final ComboBox<TaskGroup> dropdown;
    private final Button options;

    private final Button account;
    
    public HomeHeader(Session session){
        super(session.getActiveTGProperty().getValue().getGroupnameProperty().getValue());

        this.session = session;
        this.activeTGproperty = session.getActiveTGProperty(); // TODO: kus seda propertyt kasutatakse? Ilmselt saaks eventidega lahendada

        this.newtask = new Button("+");
        this.dropdown = new Dropdown(this.session);
        this.options = new OptionsButton(this);

        this.account = new Button();

        initLayout();
        initEvents();
    }
    
    private void initLayout(){
        HBox dropdownSection = new HBox(newtask, dropdown, options);

        this.getChildren().addAll(dropdownSection, this.account);
        
        // css
        this.newtask.setId("newtaskbutton");
        dropdownSection.getStyleClass().add("dropdownsection");
        dropdownSection.getStylesheets().add(getClass().getResource("/Stylesheets/Home/DropdownSection.css").toExternalForm());
        
        this.account.setId("accbutton");
    }

    private void initEvents(){
        // listeners
        this.activeTGproperty.addListener(
            (obs, oldVal, newVal) -> {
                this.title.setValue(newVal.getGroupnameProperty().getValue());
            }
        );

        // events
        this.newtask.setOnAction(e -> {
            this.newtask.fireEvent(new NewTaskEvent());
        });

        this.account.setOnAction(e -> {
            this.account.fireEvent(new ChangeSceneEvent(SceneType.ACCOUNT));
        }); 

        // handlers
        this.addEventHandler(
            NewTaskEvent.NEW_TASK,
            new NewTaskHandler(this.activeTGproperty));

        this.addEventHandler(
            NewTGEvent.NEW_TG,
            new NewTGHandler(this.session, this.getTitle(), this.dropdown)
        );

        this.addEventHandler(
            EditTGEvent.EDIT_TG,
            new EditTGHandler(this.getTitle()));

        this.addEventHandler(
            DeleteTGEvent.DELETE_TG,
            new DeleteTGHandler(this.session, this.dropdown));
    }
}
