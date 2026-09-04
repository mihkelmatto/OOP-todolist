package UI.Home;

import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import models.Session;
import models.Task;

public class MidSection {
    private ObservableList<Task> activeTGtasks;
    private ListView<Task> layout;
    private Session session;

    public MidSection(Session session){
        this.session = session;
        this.layout = new ListView<>();
        refreshItems();

        this.session.getActiveTGProperty().addListener(
            (obs, oldVal, newVal) -> {
                refreshItems();
            }
        );

        this.layout.setCellFactory(param -> new ListCell<Task>() {

            @Override
            protected void updateItem(Task task, boolean empty) {

                super.updateItem(task, empty);

                if(empty || task == null){
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new TaskCard(task, session));
                }
            }
        });
        VBox.setVgrow(layout, Priority.ALWAYS);
        this.layout.getStyleClass().add("MidSection");
    }

    private void refreshItems(){
        this.activeTGtasks = this.session.getActiveTGProperty().getValue().getTasksProperty();
        this.layout.setItems(this.activeTGtasks);
    }

    public ListView<Task> getLayout(){
        return this.layout;
    }
}
