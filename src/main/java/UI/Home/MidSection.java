package UI.Home;

import javafx.collections.transformation.SortedList;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Session;
import models.Task;

public class MidSection {
    private ScrollPane layout;
    private Session session;

    public MidSection(Session session){
        this.session = session;

        this.layout = new ScrollPane();
        refreshTaskCards();
        this.session.getActiveTGProperty().addListener(
            (obs, oldVal, newVal) -> {
                refreshTaskCards();
            }
        );

        this.layout.setFitToWidth(true);
        this.layout.getStyleClass().add("MidSection");
    }

    public void refreshTaskCards(){
        VBox taskcards = new VBox();
        taskcards.setSpacing(20);

        SortedList<Task> tasks = this.session.getActiveTGProperty().getValue().getTasksProperty();

        for(Task task : tasks){
            TaskCard card = new TaskCard(task);
            taskcards.getChildren().add(card.getLayout());
        }
        taskcards.getStyleClass().add("MidSection-VBox");
        this.layout.setContent(taskcards);
    }

    public ScrollPane getLayout(){
        return this.layout;
    }
}
