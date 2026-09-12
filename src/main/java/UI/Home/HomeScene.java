package UI.Home;

import models.Session;
import models.TaskGroup;

import utils.eventhandlers.Task.DelTaskHandler;
import utils.eventhandlers.Task.NewTaskHandler;
import utils.eventhandlers.TaskGroup.DeleteTGHandler;
import utils.eventhandlers.TaskGroup.EditTGHandler;
import utils.eventhandlers.TaskGroup.NewTGHandler;
import utils.events.Task.DelTaskEvent;
import utils.events.Task.NewTaskEvent;
import utils.events.TaskGroup.DeleteTGEvent;
import utils.events.TaskGroup.EditTGEvent;
import utils.events.TaskGroup.NewTGEvent;

import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class HomeScene extends Scene{
    private final HomeHeader header;
    private final HomeBody body;

    private final Session session;
    private final ObjectProperty<TaskGroup> activeTGProperty;

    public HomeScene(Session session){
        super(new VBox());

        this.session = session;
        this.activeTGProperty = session.getActiveTGProperty();

        this.header = new HomeHeader(this.activeTGProperty, this.session.getTGListProperty());
        this.body = new HomeBody(this.activeTGProperty);

        initLayout();
        initEvents();
    }

    private void initLayout(){
        VBox root = (VBox) this.getRoot();
        root.getChildren().addAll(this.header, this.body);

        // css
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Global.css").toExternalForm());
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Home/HomeScene.css").toExternalForm());
    }

    private void initEvents(){
        this.addEventHandler(
            NewTaskEvent.NEW_TASK,
            new NewTaskHandler(this.activeTGProperty)
        );

        this.addEventHandler(
            NewTGEvent.NEW_TG,
            new NewTGHandler(this.session, this.header.getTitle(), this.header.getDropdown())
        );

        this.addEventHandler(
            EditTGEvent.EDIT_TG,
            new EditTGHandler(this.header.getTitle())
        );

        this.addEventHandler(
            DeleteTGEvent.DELETE_TG,
            new DeleteTGHandler(this.session)
        );

        this.addEventHandler(
            DelTaskEvent.DEL_TASK,
            new DelTaskHandler(this.activeTGProperty)
        );
    }
}
