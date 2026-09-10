package UI.Home;

import utils.events.TaskGroup.DeleteTGEvent;
import utils.events.TaskGroup.EditTGEvent;
import utils.events.TaskGroup.NewTGEvent;

import javafx.event.Event;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;


public class OptionsButton extends Button{
    private final Popup popup;

    private final Button add;
    private final Button edit;
    private final Button delete;

    public OptionsButton(EventTarget target){
        this.popup = new Popup();

        this.add = new Button("new group");
        this.edit = new Button("edit title");
        this.delete = new Button("delete group");

        initLayout();
        // eventid / listenerid
        this.setOnAction(e -> togglePopup());

        this.add.setOnAction(e -> {
            Event.fireEvent(target, new NewTGEvent());
            this.popup.hide();
        });
        
        this.edit.setOnAction(e -> {
            Event.fireEvent(target, new EditTGEvent());
            this.popup.hide();
        });
    
        this.delete.setOnAction(e -> {
            Event.fireEvent(target, new DeleteTGEvent());
            this.popup.hide();
        });
    }

    private void initLayout(){
        this.setText("⋮");

        VBox popupContent = new VBox(this.add, this.edit, this.delete);
        this.popup.getContent().add(popupContent);
        this.popup.setAutoHide(true);

        // css
        this.setId("optionsbutton");
        popupContent.getStyleClass().add("optionscontent");

        popupContent.getStylesheets().add(getClass().getResource("/Stylesheets/Widgets/Header.css").toExternalForm());
        popupContent.getStylesheets().add(getClass().getResource("/Stylesheets/Home/DropdownSection.css").toExternalForm());
        
    }

    private void togglePopup(){
        if(this.popup.isShowing()){
            this.popup.hide();
            return;
        }
        
        var point = localToScreen(0, this.getHeight());

        this.popup.show(this, 0, 0);
        this.popup.setX(point.getX() + this.getWidth() - this.popup.getWidth());
        this.popup.setY(point.getY());
    }
}
