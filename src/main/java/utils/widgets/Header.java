package utils.widgets;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class Header extends HBox{
    private EditableField title;

    public Header(String titleStr, Node ... nodes){
        this.title = new EditableField(titleStr);

        initLayout(nodes);
    }

    private void initLayout(Node ... nodes){
        this.setSpacing(10);
        HBox.setHgrow(this.title, Priority.ALWAYS);

        this.getChildren().add(this.title);
        this.getChildren().addAll(nodes);
        
        // css
        this.getStyleClass().add("Header");
        this.title.getStyleClass().add("title");
        this.getStylesheets().add(getClass().getResource("/Stylesheets/Widgets/Header.css").toExternalForm());
    }

    public EditableField getTitle(){
        return this.title;
    }
}
