package UI.Account;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import utils.widgets.EditableField;

public class Row extends HBox{
    private boolean editable;

    private StackPane icon;
    private Label title;
    private EditableField description;
    private Button edit;

    public Row(String titletext, String descriptiontext, String iconpath){
        // init
        this.editable = false;
        this.icon = createIcon(iconpath);
        this.title = new Label(titletext);
        this.description = new EditableField(descriptiontext);
        this.edit = new Button();
        
        initLayout();

        // Events / listeners
        edit.setOnAction(e -> {
            this.editable = !editable;
            description.setEditable(editable);
        });
    }

    private void initLayout(){
        // layout
        this.setSpacing(10);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(icon, title, spacer, description, edit);

        // css
        this.getStyleClass().add("row");
        icon.getStyleClass().add("row-icon");
        title.getStyleClass().add("row-title");
        edit.getStyleClass().add("row-edit");
        description.getStyleClass().add("row-description");
    }

    private StackPane createIcon(String iconpath){
        ImageView icon = new ImageView(new Image(getClass().getResource("/images/" + iconpath).toExternalForm()));
        StackPane iconcontainer = new StackPane();
        iconcontainer.getChildren().add(icon);
        icon.fitWidthProperty().bind(iconcontainer.prefWidthProperty());
        icon.fitHeightProperty().bind(iconcontainer.prefHeightProperty());
        icon.setPreserveRatio(true);

        icon.getStyleClass().add("row-icon");
        iconcontainer.getStyleClass().add("row-iconcontainer");

        return iconcontainer;
    }
}
