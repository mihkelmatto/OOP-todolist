package UI.Home;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import models.Session;
import models.Task;
import models.TaskGroup;
import utils.events.ChangeSceneEvent;
import utils.events.SceneType;
import utils.widgets.EditableField;

// TODO: title joondus katki

public class Header {
    private HBox layout;

    private Session session;
    private SimpleObjectProperty<TaskGroup> activeTG;
    
    private EditableField title;
    private Button newtask;
    private ComboBox<TaskGroup> dropdowncontent;
    private ObservableList<TaskGroup> taskgroups;

    public Header(Session session){
        this.session = session;
        
        this.activeTG = this.session.getActiveTGProperty();
        this.taskgroups = this.session.getTaskgroupProperty();
        this.activeTG.addListener(
            (obs, oldVal, newVal) -> {
                this.title.setValue(newVal.getGroupnameProperty().getValue());
            }
        );
        
        this.dropdowncontent = createDropdownContent();
        this.layout = createLayout();
    }

    public HBox createLayout(){
        HBox layout = new HBox();
        layout.setSpacing(10);

        // Title
        this.title = new EditableField(this.activeTG.getValue().getGroupnameProperty().getValue());
        title.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                this.title.setEditable(false);
            }
        });
        HBox.setHgrow(title, Priority.ALWAYS);

        // dropdown section

        this.newtask = new Button("+");
        this.newtask.setOnAction(e -> {
            Task task = new Task();
            this.session.getActiveTGProperty().getValue().addTask(task); 
            task.getEditableProperty().setValue(true);     
        });
        
        HBox dropdown = new HBox();
        dropdown.getChildren().addAll(this.newtask, this.dropdowncontent);
        
        // layout settings
        layout.getChildren().addAll(title, dropdown, createGroupOptions(), createAccbutton());
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());

        layout.getStyleClass().add("Header");
        this.title.getStyleClass().add("title");
        this.newtask.setId("newtaskbutton");
        dropdown.setId("dropdownwidget");
        return layout;
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

    private Button createGroupOptions(){
        Button optionsbutton = new Button("⋮");
        Popup popup = new Popup();

        VBox vbox = new VBox();

        Button add = new Button("new group");
        add.setOnAction(e -> {
            TaskGroup newgroup = this.session.createTaskgroup();
            this.title.setEditable(true);
            this.dropdowncontent.getSelectionModel().select(newgroup);
            popup.hide();
        });
        
        Button edit = new Button("edit title");
        edit.setOnAction(e -> {
            this.title.setEditable(true);
            popup.hide();
        });

        Button delete = new Button("delete group");
        delete.setOnAction(e -> {
            TaskGroup newactive = this.session.deleteTaskgroup();
            this.dropdowncontent.getSelectionModel().select(newactive);
            popup.hide();
        });

        vbox.getChildren().addAll(add, edit, delete);
        popup.getContent().add(vbox);
        
        optionsbutton.setOnAction(e -> {
            if(popup.isShowing()){
                popup.hide();
                return;
            }
            // TODO: mingi bug, kus popup võib tekkida teisele ekraanile
            popup.show(optionsbutton, 0, 0);
            popup.setX(optionsbutton.localToScreen(0, optionsbutton.getHeight()).getX() + optionsbutton.getWidth() - popup.getWidth());
            popup.setY(optionsbutton.localToScreen(0, optionsbutton.getHeight()).getY() + 5);
        });

        vbox.setId("options");
        optionsbutton.setId("optionsbutton");
        popup.setAutoHide(true);
        vbox.getStylesheets().add(getClass().getResource("/Stylesheets/Header.css").toExternalForm());

        return optionsbutton;
    }
    
    private ComboBox<TaskGroup> createDropdownContent(){
        ComboBox<TaskGroup> content = new ComboBox<>(this.taskgroups);
        
        content.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(TaskGroup tg, boolean empty) {
                super.updateItem(tg, empty);
                textProperty().unbind();
                
                if (empty || tg == null) {
                    setText(null);
                } else {
                    textProperty().bind(tg.getGroupnameProperty());
                }
            }
        });
        
        content.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(TaskGroup tg, boolean empty) {
                super.updateItem(tg, empty);
                textProperty().unbind();
                
                if (empty || tg == null) {
                    setText(null);
                } else {
                    textProperty().bind(tg.getGroupnameProperty());
                }
            }
        });

        content.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.activeTG.setValue(newValue);
        });
        
        content.setValue(this.activeTG.getValue());

        return content;
    }

    public Button getNewtaskButton(){
        return this.newtask;
    }
    
    public HBox getLayout(){
        return this.layout;
    }
}
