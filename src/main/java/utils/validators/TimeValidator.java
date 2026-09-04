package utils.validators;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeValidator implements Validator{

    @Override
    public boolean validate(String time){
        DateTimeFormatter timeformat = DateTimeFormatter.ofPattern("HH.mm");
        try{
            LocalTime.parse(time, timeformat);
            return true;
        }
        catch(DateTimeParseException e){
            System.out.printf("DateTimeParseException at TimeValidator.validate(): %s \n", e);
            return false;
        }
    }
}
