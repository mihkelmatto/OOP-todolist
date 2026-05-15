package UI.Home;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.Session;
import models.Task;
import models.TaskGroup;

public class MidSection {
    private ScrollPane layout;
    private Session session;
    private TaskGroup activeTG;

    public MidSection(Session session){
        this.session = session;
        this.activeTG = this.session.getTaskgroups().values().iterator().next();

        VBox content = new VBox();
        content.setSpacing(10);

        for(Task task : this.activeTG.getTasks()){
            TaskCard card = new TaskCard(task);
            content.getChildren().add(card.getLayout());
        }
        
        // content.getStyleClass().add("MidSection");
        this.layout = new ScrollPane(content);
        this.layout.setFitToWidth(true);
        this.layout.getStyleClass().add("MidSection");
    }

    public ScrollPane getLayout(){
        return this.layout;
    }
}
