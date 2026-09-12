package UI.Account.Section;

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

    public Section(String title){
        this.title = new Label(title);
        this.content = new VBox();
        
        initLayout();
    }
    
    private void initLayout(){
        this.setSpacing(10);
        this.content.setSpacing(10);
        
        this.getChildren().addAll(this.title, this.content);
        
        // css
        this.title.getStyleClass().add("section-title");
        this.content.getStyleClass().add("section-content");
    }

    protected void addRow(Row row){
        if(!this.content.getChildren().isEmpty()){
            this.content.getChildren().add(new Separator());
        }

        this.content.getChildren().add(row);
    }
}
