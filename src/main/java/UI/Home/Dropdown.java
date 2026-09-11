package UI.Home;

import models.Session;
import models.TaskGroup;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;

public class Dropdown extends ComboBox<TaskGroup>{
    private final ObjectProperty<TaskGroup> activeTGProperty;

    public Dropdown(Session session){
        super(session.getTGListProperty());

        this.activeTGProperty = session.getActiveTGProperty();

        init();
    }

    private void init(){
        this.setCellFactory(lv -> createTGCell());
        this.setButtonCell(createTGCell());
        
        // TODO: bindBiDirectional?
        this.valueProperty().addListener((obs, oldValue, newValue) -> {
            this.activeTGProperty.setValue(newValue);
        });
        
        this.setValue(this.activeTGProperty.getValue());
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
                    textProperty().bind(tg.getGroupnameProperty());
                }
            }
        };
    }    
}
