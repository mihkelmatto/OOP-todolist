package utils.validators;

public class NotEmptyValidator implements Validator{
    public boolean validate(String input){
        if(input.isBlank()) return false;
        else return true;
    }   
}
