package UI.Home;

import java.util.ArrayList;

import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Session;
import models.Task;
import models.TaskGroup;

public class MidSection {
    private ScrollPane layout;
    private Session session;
    private SimpleObjectProperty<TaskGroup> activeTG;

    public MidSection(Session session){
        this.session = session;
        this.activeTG = session.getActiveTGProperty();

        this.layout = new ScrollPane();
        refreshTaskCards();
        
        this.layout.setFitToWidth(true);
        this.layout.getStyleClass().add("MidSection");
    }

    public void refreshTaskCards(){
        VBox taskcards = new VBox();
        taskcards.setSpacing(20);

        ArrayList<Task> tasks = this.activeTG.getValue().getTasks();

        for(Task task : tasks){
            TaskCard card = new TaskCard(task);
            taskcards.getChildren().add(card.getLayout());
        }
        this.layout.setContent(taskcards);
    }

    public ScrollPane getLayout(){
        return this.layout;
    }
}
