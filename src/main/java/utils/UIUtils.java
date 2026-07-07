package utils;

import javafx.scene.control.TextField;

public class UIUtils {
    public static TextField createTextfield(String in, String cssID){
        TextField field = new TextField(in);
        field.setEditable(false);
        field.setFocusTraversable(false);
        field.setId(cssID);

        return field;
    }

    public static void toggleEditable(TextField ... fields){
        for(TextField field : fields)
        if(field.isEditable()){
            field.setEditable(false);
            field.setFocusTraversable(false);
            field.getStyleClass().remove("editable");
        }
        else{
            field.setEditable(true);
            field.setFocusTraversable(true);
            field.getStyleClass().add("editable");
        }
    }
}
