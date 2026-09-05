package UI.Account;

import models.User;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AccountBody extends ScrollPane{
    private User user;
    
    public AccountBody(User user){
        this.user = user;

        initLayout();

        // events / listeners

    }

    private void initLayout(){
        this.setFitToHeight(true);
        this.setFitToWidth(true);

        VBox content = new VBox();
        content.setSpacing(10);

        content.getChildren().addAll(
            new Section("Kasutajainfo", createUserRows()),
            new Section("Eelistused", createPrefRows()), 
            new Section("Lisavalikud", createAdvRows())
        );

        this.setContent(content);

        // css
        this.getStyleClass().add("scrollable");
        content.getStyleClass().add("scrollable-content");        
    }

    private Row[] createUserRows(){
        return new Row[] {
            new Row("Kasutajanimi", this.user.getUsernameProperty().getValue(), "windowicon.png"),
            new Row("Parool", "Viimati muudetud: 10.07.2026", "windowicon.png"),
            new Row("E-post", "", "windowicon.png")
        };        
    }

    private Row[] createPrefRows(){
        return new Row[] {
            new Row("Teavitused", "", "windowicon.png"),
            new Row("Keel", "", "windowicon.png")
        };
    }

    private Row[] createAdvRows(){
        return new Row[]{
            new Row("Kustuta konto", "", "windowicon.png")
        };
    }
}
