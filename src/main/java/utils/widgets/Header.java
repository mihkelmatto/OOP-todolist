package utils.widgets;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Header extends HBox{
    protected EditableField title;

    public Header(String titleStr){
        this.setSpacing(10);

        // title
        this.title = new EditableField(titleStr, "title");

        HBox.setHgrow(this.title, Priority.ALWAYS);
        this.getChildren().add(this.title);
        
        // css
        this.getStyleClass().add("Header");
        this.title.getStyleClass().add("title");
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Widgets/Header.css").toExternalForm());
    }

    public Header(String titleStr, Node ... nodes){
        this(titleStr);
        this.getChildren().addAll(nodes);
    }

    public void EditTitle(){
        this.title.setEditable(true);
    }

    public EditableField gettitle(){
        return this.title;
    }
}
