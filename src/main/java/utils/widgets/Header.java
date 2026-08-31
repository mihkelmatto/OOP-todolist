package utils.widgets;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Header extends HBox{
    private EditableField titlefield;

    public Header(){
        this("title");
    }

    public Header(String title){
        this.setSpacing(10);

        // title
        this.titlefield = new EditableField(title, "title");
        HBox.setHgrow(this.titlefield, Priority.ALWAYS);
        this.getChildren().add(this.titlefield);
        
        // css
        this.getStyleClass().add("Header");
        this.titlefield.getStyleClass().add("title");
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Widgets/Header.css").toExternalForm());
    }

    public Header(String title, Node ... nodes){
        this(title);
        this.getChildren().addAll(nodes);
    }

    public void EditTitle(){
        this.titlefield.setEditable(true);
    }

    public EditableField getTitleField(){
        return this.titlefield;
    }
}
