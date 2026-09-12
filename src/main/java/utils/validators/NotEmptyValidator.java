package utils.validators;

public class NotEmptyValidator implements Validator{
    public ValidationResult validate(String input){
        if(input.isBlank()){
            return ValidationResult.EMPTY;
        }
        else{
            return ValidationResult.VALID;
        }
    }   
}
