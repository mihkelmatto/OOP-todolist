package UI.Home;

import models.Session;
import models.TaskGroup;


import utils.eventhandlers.TaskHandler;
import utils.eventhandlers.TGHandler;

import utils.events.Task.TaskEvent;
import utils.events.TaskGroup.TGevent;

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
            TaskEvent.ANY,
            new TaskHandler(this.activeTGProperty)
        );

        this.addEventHandler(
            TGevent.ANY,
            new TGHandler(this.session, this.header.getTitle())
        );
    }
}
