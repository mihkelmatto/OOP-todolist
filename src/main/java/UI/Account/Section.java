package UI.Account;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;

/*
    Loob sektsiooni, millel on pealkiri ja sisendile vastav arv ridasid (Row objekte)
    
    Visuaalselt tekib kast, mille sees on Row objektid ning kasti kohal on pealkiri (kasti piiridest väljas)
*/

public class Section extends VBox{
    private Label title;
    private VBox content;

    public Section(String title, Row ... rows){
        this.title = new Label(title);
        this.content = new VBox();
        
        initLayout(rows);
    }
    
    private void initLayout(Row ... rows){
        this.setSpacing(10);
        this.content.setSpacing(10);
        
        for(int i = 0; i<rows.length; i++){
            this.content.getChildren().add(rows[i]);
            if(i<rows.length-1) this.content.getChildren().add(new Separator());
        }

        this.getChildren().addAll(this.title, this.content);
        
        // css
        this.title.getStyleClass().add("section-title");
        this.content.getStyleClass().add("section-content");
    }
}
