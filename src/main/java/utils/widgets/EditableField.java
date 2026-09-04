package utils.widgets;

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import utils.validators.NotEmptyValidator;
import utils.validators.Validator;

/*
    Label, mida saab muudetavaks teha
    
    Vaikimisi editable = false, st. nähtaval on label
        kui editable = true, siis on nähtaval hoopis textfield (label.setvisible = false)
        TextFieldis enteri vajutamisel kutsutakse setEditable(false)

    Update tsükkel: valuefield -> validate -> valueproperty -> valuelabel
    - setEditable(false) -> valueField väärtus uuendab valueProperty väärtust, kui validator annab true
    - valueProperty muutumine uuendab valueLabel väärtust

    Validator:
    - default validator kontrollib, kas sisend on tühi (v.a. whitespace)
    - validatori määramiseks on konstruktori asemel setValidator(Validator validator)
*/

public class EditableField extends StackPane{
    private Label valueLabel;
    private TextField valueField;
    private SimpleStringProperty valueProperty;

    private Validator validator = new NotEmptyValidator();
    private boolean editable;

    public EditableField(SimpleStringProperty valueProperty, String cssClassname){
        this.valueProperty = valueProperty;
        this.valueLabel = createValueLabel();
        this.valueField = createValuefield();
        
        this.getStyleClass().add(cssClassname);
        this.valueLabel.getStyleClass().addAll("value", "valuelabel");
        this.valueField.getStyleClass().addAll("value", "valuefield");
        
        getChildren().addAll(this.valueLabel, this.valueField);

        StackPane.setAlignment(valueLabel, Pos.CENTER_LEFT);
        StackPane.setAlignment(valueField, Pos.CENTER_LEFT);

        this.editable = false;
        this.valueLabel.setVisible(true);
        this.valueField.setVisible(false);
    }

    public EditableField(SimpleStringProperty valueProperty){
        this(valueProperty, "editablefield");
    }

    //

    private Label createValueLabel(){
        Label label = new Label();

        label.textProperty().bind(this.valueProperty);
        label.prefWidthProperty().bind(this.widthProperty());

        return label;
    }

    private TextField createValuefield(){
        TextField field = new TextField();

        field.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.ENTER) {
                this.setEditable(false);
            }
        });

        return field;
    }

    public void toggleEditable(){
        this.editable = !this.editable;
        setEditable(this.editable);
    }

    // SETTERS

    public void setEditable(boolean editable){
        this.editable = editable;

        if(editable){
            this.valueLabel.setVisible(false);
            this.valueField.setVisible(true);
            this.valueField.requestFocus();
        }

        else{
            String input = this.valueField.getText();
            
            if(validator.validate(input)){
                this.valueProperty.setValue(input);
            }
            else{
                System.out.println("EditableField: Validation failed");
            }
            
            this.valueLabel.setVisible(true);
            this.valueField.setVisible(false);
        }
    }

    public void setValidator(Validator validator){
        this.validator = validator;
    }

    public void setValue(String value){
        this.valueProperty.setValue(value);
    }

    // GETTERS

    public TextField getValueField(){
        return this.valueField;
    }

    public String getValue(){
        return this.valueProperty.getValue();
    }
    
    public boolean isEditable(){
        return this.editable;
    }

}
