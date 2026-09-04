package UI.Account;

import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import models.User;

public class AccountScene {
    private Scene scene;

    private User user;

    public AccountScene(User user){
        this.user = user;
        
        VBox layout = new VBox();
        ScrollPane content = createContent();
        VBox.setVgrow(content, Priority.ALWAYS);
        layout.getChildren().addAll(new Header(), content);

        this.scene = new Scene(layout);
        this.scene.getStylesheets().add(getClass().getResource("/Stylesheets/Global.css").toExternalForm());
        this.scene.getStylesheets().add(getClass().getResource("/Stylesheets/Account/AccountScene.css").toExternalForm());
    }

    private ScrollPane createContent(){
        VBox content = new VBox();
        content.setSpacing(10);

        ScrollPane scrollable = new ScrollPane(content);
        scrollable.setFitToHeight(true);
        scrollable.setFitToWidth(true);

        Section profileSection = createProfileSection();
        Section prefSection = createPrefSection();
        Section advSection = createAdvSection();

        content.getChildren().addAll(
            profileSection.getTitle(), profileSection.getLayout(), 
            prefSection.getTitle(), prefSection.getLayout(),
            advSection.getTitle(), advSection.getLayout()
        );

        scrollable.getStyleClass().add("scrollable");
        content.getStyleClass().add("content");

        return scrollable;
    }



    private Section createProfileSection(){
        String subtitle = "Profiil";
        Row[] items = {
            new Row("Kasutajanimi", user.getUsernameProperty().getValue(), "windowicon.png"),
            new Row("Parool", "Viimati muudetud: 10.07.2026", "windowicon.png"),
            new Row("E-post", "", "windowicon.png")
        };
        return new Section(subtitle, items);
    }

    private Section createPrefSection(){
        String subtitle = "Eelistused";
        Row[] items = {
            new Row("Teavitused", "", "windowicon.png"),
            new Row("Keel", "", "windowicon.png")
        };
        return new Section(subtitle, items);
    }

    private Section createAdvSection(){
        String subtitle = "Lisavalikud";
        Row[] items = {
            new Row("Kustuta konto", "", "windowicon.png")
        };
        return new Section(subtitle, items);
    }

    public Scene getScene(){
        return this.scene;
    }
}
