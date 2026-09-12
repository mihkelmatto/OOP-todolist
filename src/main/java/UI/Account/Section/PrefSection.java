package UI.Account.Section;

public class PrefSection extends Section{
    
    public PrefSection(){
        super("Eelistused");
    
        initLayout();
    }

    private void initLayout(){
        this.addRow(new Row(
            "Teavitused",
            "",
            "accounticon.path"
        ));

        this.addRow(new Row(
            "Keel",
            "",
            "accounticon.path"
        ));
    }
}
