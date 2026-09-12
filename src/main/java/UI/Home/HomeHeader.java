package UI.Home;

import models.TaskGroup;

import utils.events.SceneEvent.ChangeSceneEvent;
import utils.events.SceneEvent.SceneType;
import utils.events.Task.NewTaskEvent;
import utils.widgets.svg.SVGButton;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class HomeHeader extends utils.widgets.Header{
    private final ObjectProperty<TaskGroup> activeTGproperty;

    private final Button newtask;
    private final ComboBox<TaskGroup> dropdown;
    private final Button options;

    private final Button account;
    
    public HomeHeader(ObjectProperty<TaskGroup> activeTGProperty, ObservableList<TaskGroup> TGlistProperty){
        super(activeTGProperty.getValue().getTitleProperty().getValue());

        this.activeTGproperty = activeTGProperty;

        this.newtask = new Button("+");
        this.dropdown = new Dropdown(TGlistProperty, this.activeTGproperty);
        this.options = new OptionsButton(this);

        this.account = new SVGButton("accounticon.path");

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
        this.activeTGproperty.addListener((obs, oldVal, newVal) -> {
            this.getTitle().setValue(newVal.getTitleProperty().getValue());
        });

        // events
        this.newtask.setOnAction(e -> {
            this.newtask.fireEvent(new NewTaskEvent());
        });

        this.account.setOnAction(e -> {
            this.account.fireEvent(new ChangeSceneEvent(SceneType.ACCOUNT));
        }); 

        this.getTitle().setOnEditComplete(() -> {
            String newTGtitle = this.getTitle().getValue();
            this.activeTGproperty.getValue().getTitleProperty().setValue(newTGtitle);
        });

    }

    // GETTERS
    public ComboBox<TaskGroup> getDropdown(){
        return this.dropdown;
    }
}
