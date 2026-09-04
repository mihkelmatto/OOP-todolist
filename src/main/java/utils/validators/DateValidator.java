package utils.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateValidator implements Validator{
    
    @Override
    public boolean validate(String date){
        DateTimeFormatter dateformat = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try{
            LocalDate.parse(date, dateformat);
            return true;
        }
        catch(DateTimeParseException e){
            System.out.printf("DateTimeParseException at DateValidator.validate(): %s \n", e);
            return false;
        }
    }
}