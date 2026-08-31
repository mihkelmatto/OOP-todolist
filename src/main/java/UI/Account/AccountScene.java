package UI.Account;

import javafx.beans.property.SimpleStringProperty;
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
        this.scene.getStylesheets().add(getClass().getResource("/Stylesheets/AccountScene.css").toExternalForm());
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
            new Row("Kasutajanimi", user.getUsernameProperty(), "windowicon.png"),
            new Row("Parool", new SimpleStringProperty("Viimati muudetud: 10.07.2026"), "windowicon.png"), // TODO: luua property
            new Row("E-post", new SimpleStringProperty(""), "windowicon.png")
        };
        return new Section(subtitle, items);
    }

    private Section createPrefSection(){
        String subtitle = "Eelistused";
        Row[] items = {
            new Row("Teavitused", new SimpleStringProperty(""), "windowicon.png"),
            new Row("Keel", new SimpleStringProperty(""), "windowicon.png")
        };
        return new Section(subtitle, items);
    }

    private Section createAdvSection(){
        String subtitle = "Lisavalikud";
        Row[] items = {
            new Row("Kustuta konto", new SimpleStringProperty(""), "windowicon.png")
        };
        return new Section(subtitle, items);
    }

    public Scene getScene(){
        return this.scene;
    }
}
