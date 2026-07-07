package UI.Home;
import models.Session;
import models.Task;
import models.TaskGroup;
import utils.UIUtils;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class TaskCard {
    private Session session;
    private Task task;

    private HBox layout;

    private TextField title;
    private TextField description;
    private DLwidget dlwidget;
    
    private Button options;
    

    public TaskCard(Task task, Session session){
        this.task = task;
        this.session = session;
        this.dlwidget = new DLwidget(session, task);

        this.layout = createLayout();
    }

    private HBox createLayout(){
        HBox layout = new HBox();
        layout.setSpacing(25);


        // Complete task button
        Button complete = new Button();
        complete.setOnAction(e -> {
            TaskGroup activeTG = this.session.getActiveTGProperty().getValue();
            activeTG.removeTask(this.task);
        });

        complete.setId("Completebutton");
        HBox.setMargin(complete, new Insets(20, 10, 0, 0));

        // Content area
        VBox contentarea = new VBox();
        this.title = UIUtils.createTextfield(this.task.getTitleProperty().getValue(), "title");
        this.description = UIUtils.createTextfield(this.task.getDescriptionProperty().getValue(), "description");

        contentarea.getStyleClass().add("contentarea");
        contentarea.getChildren().addAll(title, description);
        HBox.setHgrow(contentarea, Priority.ALWAYS);

        // options
        VBox optionsbox = new VBox();
        this.options = new Button(); // ⋮

        this.options.setOnAction(e -> {
            if(title.isEditable()){
                this.task.updateTitle(title.getText());
                this.task.updateDescription(description.getText());
            }

            UIUtils.toggleEditable(this.title, this.description);
            this.dlwidget.toggleEditable();
        });

        optionsbox.getChildren().add(options);
        optionsbox.getStyleClass().add("Taskcard-optionsbox");

        // deadline area
        HBox dlLayout = this.dlwidget.getLayout();
        HBox.setMargin(dlLayout, new Insets(10, 0, 10, 0));

        // main layout
        layout.getChildren().addAll(complete, contentarea, dlLayout, optionsbox);
        layout.getStyleClass().add("TaskCard");
        layout.getStylesheets().add(getClass().getResource("/Stylesheets/TaskCard.css").toExternalForm());
        return layout;
    }

    public HBox getLayout(){
    return this.layout;
   }
}
