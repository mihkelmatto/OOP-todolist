package utils.widgets;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;

public class EditableField extends StackPane{
    private Label valueLabel;
    private TextField valueField;
    private SimpleStringProperty valueProperty;
    private boolean editable;

    public EditableField(String value, String cssClassname){
        this.valueProperty = new SimpleStringProperty(value);
        this.valueLabel = createValueLabel();
        this.valueField = createValuefield();
        this.setEditable(false);

        this.getStyleClass().add(cssClassname);
        this.valueField.getStyleClass().add("editable");

        getChildren().addAll(this.valueLabel, this.valueField);
    }

    public EditableField(String value){
        this(value, "editablefield");
    }

    public EditableField(){
        this("");
    }

    private Label createValueLabel(){
        Label label = new Label();

        label.setText(this.valueProperty.getValue());
        label.textProperty().bindBidirectional(this.valueProperty);
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
        field.prefWidthProperty().bind(this.widthProperty());

        return field;
    }

    public void toggleEditable(){
        this.editable = !this.editable;
        setEditable(this.editable);
    }

    public void setEditable(boolean editable){
        this.editable = editable;

        if(editable){
            this.valueLabel.setVisible(false);
            this.valueField.setVisible(true);
            this.valueField.requestFocus();
        }

        else{
            if(!this.valueField.getText().isBlank()){
                this.valueProperty.set(this.valueField.getText());
            }
            this.valueField.clear();

            this.valueLabel.setVisible(true);
            this.valueField.setVisible(false);
        }
    }

    public void setValue(String value){
        this.valueProperty.setValue(value);
    }

    public SimpleStringProperty getValueProperty(){
        return this.valueProperty;
    }

    public TextField getValueField(){
        return this.valueField;
    }
}
