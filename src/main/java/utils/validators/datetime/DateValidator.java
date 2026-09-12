package utils.validators.datetime;

import UI.Home.DLwidget;
import utils.validators.ValidationResult;
import utils.validators.Validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/*
    tingimused:
    - vastab DLWidget poolt määratud ajaformaadile
    
*/

public class DateValidator implements Validator{
    
    @Override
    public ValidationResult validate(String input){
        try{
            LocalDate.parse(input, DLwidget.getDateformat());
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