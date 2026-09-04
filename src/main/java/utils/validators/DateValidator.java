package utils.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import UI.Home.DLwidget;

public class DateValidator implements Validator{
    
    @Override
    public boolean validate(String date){
        DateTimeFormatter dateformat = DLwidget.getDateformat();
        try{
            LocalDate.parse(date, dateformat);
            return true;
        }
        catch(DateTimeParseException e){
            if(!date.isBlank()){
                System.out.printf("DateTimeParseException at DateValidator.validate(): %s \n", e);
            }
            return false;
        }
    }
}