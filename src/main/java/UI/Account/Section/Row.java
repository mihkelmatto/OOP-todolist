package UI.Account.Section;

import utils.validators.NotEmptyValidator;
import utils.validators.Validator;
import utils.widgets.EditableField;
import utils.widgets.svg.SVGButton;
import utils.widgets.svg.SVGIcon;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

/*
    Igas reas on ikoon, pealkiri, sisu ja nupp.

    Sisu saab muuta nupu abil, mis kutsub description.setEditable(boolean editable)
    Eduka valideerimise puhul muudetakse descriptioni väärtust ning kutsutakse this.onEditComplete.run()
*/

public class Row extends HBox{
    private boolean editable;

    private final SVGIcon icon;
    private final Label title;
    private final EditableField description;
    private final Button edit;

    private Validator validator = new NotEmptyValidator();
    private Runnable onEditComplete = () -> {};


    public Row(String titletext, String descriptiontext, String iconpath){
        this.editable = false;

        this.icon = new SVGIcon(iconpath);
        this.title = new Label(titletext);
        this.description = new EditableField(descriptiontext);
        this.edit = new SVGButton("editicon.path");
        
        initLayout();

        // Events / listeners
        edit.setOnAction(e -> {
            this.editable = !editable;
            description.setEditable(editable);
        });

        this.description.getValueProperty().addListener(e -> {
            this.onEditComplete.run();
        });
    }

    private void initLayout(){
        this.description.setValidator(this.validator);
        
        // layout
        this.setSpacing(10);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        this.getChildren().addAll(icon, title, spacer, description, edit);

        // css
        this.getStyleClass().add("row");

        this.title.getStyleClass().add("row-title");
        this.description.getStyleClass().add("row-description");
    }

    // SETTERS

    public void setValidator(Validator validator){
        this.validator = validator;
        this.description.setValidator(validator);
    }

    public void setOnEditComplete(Runnable action){
        this.onEditComplete = action;
    }
}
