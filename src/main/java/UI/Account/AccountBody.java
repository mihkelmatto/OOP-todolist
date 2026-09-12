package UI.Account;

import models.User;

import UI.Account.Section.UserSection;
import UI.Account.Section.AdvSection;
import UI.Account.Section.PrefSection;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class AccountBody extends ScrollPane{
    private final User user;
    
    public AccountBody(User user){
        this.user = user;

        initLayout();

        // events / listeners

    }

    private void initLayout(){
        this.setFitToHeight(true);
        this.setFitToWidth(true);

        VBox content = new VBox(
            10,
            new UserSection(this.user),
            new PrefSection(), 
            new AdvSection()
        );

        this.setContent(content);

        // css
        this.getStyleClass().add("scrollable");
        content.getStyleClass().add("scrollable-content");        
    }
}
