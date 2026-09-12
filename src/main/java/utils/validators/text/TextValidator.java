package utils.validators.text;

import utils.validators.ValidationResult;
import utils.validators.Validator;

/*
    tingimused:
    - ei ole tühi (või ainult whitespace)
    - pikkus on minlen - maxlen
    - esimene ja viimane karakter ei tohi olla tühik
    - lubatud karakterid:
        unicode tähed,
        unicode numbrid,
        SPECIAL_CHARS
*/

public class TextValidator implements Validator{
    private String SPECIAL_CHARS = "!@#$%^&*()-_+ ";

    private int minlen;
    private int maxlen;

    public TextValidator(int minlen, int maxlen, String SPECIAL_CHARS){
        this(minlen, maxlen);
        this.SPECIAL_CHARS = SPECIAL_CHARS;
    }

    public TextValidator(int minlen, int maxlen){
        this.minlen = minlen;
        this.maxlen = maxlen;
    }
    
    @Override
    public ValidationResult validate(String input){
        if(input == null) return ValidationResult.NULL;

        if(input.isBlank()) return ValidationResult.EMPTY;

        int length = input.length();
        if(length < this.minlen || length > this.maxlen){
            return ValidationResult.INVALID_LENGTH;
        }

        if(input.charAt(0) == ' ' || input.charAt(length - 1) == ' '){
            return ValidationResult.INVALID_FORMAT;
        }

        for(char c : input.toCharArray()){
            if(!Character.isLetterOrDigit(c) && SPECIAL_CHARS.indexOf(c) == -1){
                return ValidationResult.INVALID_CHARACTERS;
            }
        }

        return ValidationResult.VALID;
    }
}
