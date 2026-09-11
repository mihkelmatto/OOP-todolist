package UI.Home;

import models.TaskGroup;

import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class Dropdown extends ComboBox<TaskGroup>{

    public Dropdown(ObservableList<TaskGroup> TGlistProperty, ObjectProperty<TaskGroup> activeTGproperty){
        super(TGlistProperty);

        init();

        // eventid / listenerid
        this.valueProperty().bindBidirectional(activeTGproperty);
    }

    private void init(){
        this.setCellFactory(lv -> createTGCell());
        this.setButtonCell(createTGCell());
    }

    private ListCell<TaskGroup> createTGCell() {
        return new ListCell<>() {
            @Override
            protected void updateItem(TaskGroup tg, boolean empty) {
                super.updateItem(tg, empty);
                textProperty().unbind();

                if (empty || tg == null) {
                    setText(null);
                } else {
                    textProperty().bind(tg.getTitleProperty());
                }
            }
        };
    }    
}
