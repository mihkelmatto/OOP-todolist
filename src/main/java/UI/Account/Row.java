package UI.Account;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import utils.UIUtils;

public class Row {
    private HBox layout;

    public Row(String titletext, SimpleStringProperty descriptiontext, String iconpath){
        this.layout = new HBox();
        this.layout.setSpacing(10);
        
        StackPane icon = createIcon(iconpath);
        Label title = new Label(titletext);
        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        TextField description = UIUtils.createTextfield(descriptiontext.getValue(), "row-description");
        Button edit = new Button();
        edit.setOnAction(e -> {
            UIUtils.toggleEditable(description);
        });

        descriptiontext.addListener(
            (obs, oldVal, newVal) -> {
                description.setText(newVal);
            }
        );

        this.layout.getStyleClass().add("row");
        icon.getStyleClass().add("row-icon");
        title.getStyleClass().add("row-title");
        edit.getStyleClass().add("row-edit");

        this.layout.getChildren().addAll(icon, title, spacer, description, edit);
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

    public HBox getLayout(){
        return this.layout;
    }
}
