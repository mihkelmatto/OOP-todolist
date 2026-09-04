package utils.validators;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import UI.Home.DLwidget;

public class TimeValidator implements Validator{

    @Override
    public boolean validate(String time){
        DateTimeFormatter timeformat = DLwidget.getTimeformat();
        try{
            LocalTime.parse(time, timeformat);
            return true;
        }
        catch(DateTimeParseException e){
            if(!time.isBlank()){
                System.out.printf("DateTimeParseException at TimeValidator.validate(): %s \n", e);
            }
            return false;
        }
    }
}
