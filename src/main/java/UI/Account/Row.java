package UI.Account;

import utils.widgets.EditableField;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

/*
    Igas reas on ikoon, pealkiri, sisu ja nupp.

    Sisu saab muuta nupu abil, mis kutsub description.setEditable(boolean editable)
    - editable = true: EditableField võtab sisendi
    - editable = false: EditableField valideerib sisendi ning salvestab enda StringProperty sisse.
        Seejärel kutsub iga nupp meetodeid vastavalt oma ülesandele.
*/

public class Row extends HBox{
    private boolean editable;

    private StackPane icon;
    private Label title;
    private EditableField description;
    private Button edit;

    public Row(String titletext, String descriptiontext, String iconpath){
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

        this.icon.getStyleClass().add("row-icon");
        this.title.getStyleClass().add("row-title");
        this.description.getStyleClass().add("row-description");
        this.edit.getStyleClass().add("row-edit");
    }

    private StackPane createIcon(String iconpath){
        ImageView image = new ImageView(new Image(getClass().getResource("/images/" + iconpath).toExternalForm()));

        StackPane iconcontainer = new StackPane();
        iconcontainer.getChildren().add(image);

        image.fitWidthProperty().bind(iconcontainer.prefWidthProperty());
        image.fitHeightProperty().bind(iconcontainer.prefHeightProperty());
        image.setPreserveRatio(true);

        return iconcontainer;
    }
}
