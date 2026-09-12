package utils.validators.datetime;

import UI.Home.DLwidget;
import utils.validators.ValidationResult;
import utils.validators.Validator;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/*
    tingimused:
    - vastab DLWidget poolt määratud ajaformaadile

*/

public class TimeValidator implements Validator{

    @Override
    public ValidationResult validate(String input){
        try{
            LocalTime.parse(input, DLwidget.getTimeformat());
            return ValidationResult.VALID;
        }
        catch(DateTimeParseException e){
            if(input.isBlank()){
                return ValidationResult.EMPTY;
            }
            else{
                return ValidationResult.INVALID_FORMAT;
            }
        }
    }
}
