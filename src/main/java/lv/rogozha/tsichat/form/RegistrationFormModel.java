package lv.rogozha.tsichat.form;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RegistrationFormModel {
    
    @Size(min=5, max=16, message="Длина логина должна быть от 5 до 16 символов")
    @Pattern(regexp="^[a-zA-Z0-9]+", message="Логин должен состоять из латинских букв и/или цифр")
    private String userLogin;
    
    @Size(min=5, max=30, message="Длина пароля должна быть от 5 до 30 символов")
    private String userPassword;
    
    private String userPasswordRepeat; 

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPasswordRepeat() {
        return userPasswordRepeat;
    }

    public void setUserPasswordRepeat(String userPasswordRepeat) {
        this.userPasswordRepeat = userPasswordRepeat;
    }
}
