package UI.Home;

import models.Task;
import models.TaskGroup;
import javafx.beans.property.ObjectProperty;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class HomeBody extends ListView<Task>{
    private ObjectProperty<TaskGroup> activeTGproperty;

    public HomeBody(ObjectProperty<TaskGroup> activeTGproperty){
        this.activeTGproperty = activeTGproperty;

        initLayout();
        initCellFactory();
        
        // events / listeners
        this.activeTGproperty.addListener(e -> {
            this.setItems(this.activeTGproperty.getValue().getTasksProperty());
        });


    }
    
    private void initLayout(){
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setItems(this.activeTGproperty.getValue().getTasksProperty());
        
        // css
        this.getStyleClass().add("MidSection");
    }

    private void initCellFactory(){
        this.setCellFactory(param -> new ListCell<Task>() {

            private TaskCard currentCard;

            @Override
            protected void updateItem(Task task, boolean empty) {

                super.updateItem(task, empty);

                if(currentCard != null) {
                    currentCard.dispose();
                    currentCard = null;
                }

                if(empty || task == null){
                    setGraphic(null);
                    setText(null);
                    return;
                } 

                currentCard = new TaskCard(task);
                setGraphic(currentCard);
                
                if(task.isnew()){
                    currentCard.setEditable(true);
                    task.consumeNew();
                }
            }
        });
    }
}
