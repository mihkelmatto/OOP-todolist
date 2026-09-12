package utils.validators.text;
/*
    Tingimused:

    - pikkus: 10 - 20 karakterit
    - lubatud karakterid:
        numbrid,
        tähemärgid,
        SPECIAL_CHARS = "!@#$%^&*()-_+ "
*/

public class PasswordValidator extends TextValidator{
    public PasswordValidator(){
        super(10, 20, "!@#$%^&*()-_+ ");
    }
}
