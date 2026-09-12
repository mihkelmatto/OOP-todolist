package UI.Account.Section;

public class AdvSection extends Section {
    
    public AdvSection(){
        super("Lisavalikud");

        initLayout();
    }

    private void initLayout(){
        this.addRow(new Row(
            "Kustuta konto",
            "",
            "accounticon.path"
        ));
    }
}
