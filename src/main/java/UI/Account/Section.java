package UI.Account;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

public class Section {
    private VBox layout;
    private Label title;

    public Section(String title, Row[] rows){
        this.layout = new VBox();
        this.layout.setSpacing(10);
        this.title = createTitle(title);

        for(int i = 0; i<rows.length; i++){
            this.layout.getChildren().add(rows[i]);
            if(i<rows.length-1) this.layout.getChildren().add(new Separator());
        }

        this.layout.getStyleClass().add("section");
    }

    private Label createTitle(String text){
        Label title = new Label(text);
        title.getStyleClass().add("section-title");

        return title;
    }

    public Label getTitle(){
        return this.title;
    }

    public VBox getLayout(){
        return this.layout;
    }
}
