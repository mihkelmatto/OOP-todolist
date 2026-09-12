package UI.Account.Section;

import models.User;

public class UserSection extends Section{
    private final User user;

    public UserSection(User user){
        super("Kasutajainfo");
        this.user = user;

        initLayout();
    }

    private void initLayout(){
        this.addRow(new Row(
            "Kasutajanimi",
            this.user.getUsernameProperty().getValue(),
            "accounticon.path"
        ));

        this.addRow(new Row(
            "Parool",
            "Viimati muudetud: 10.07.2026",
            "accounticon.path"
        ));

        this.addRow(new Row(
            "E-post",
            "",
            "accounticon.path"
        ));
    }
}
