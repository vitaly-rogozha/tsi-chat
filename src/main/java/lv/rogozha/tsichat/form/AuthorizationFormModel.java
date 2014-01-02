package lv.rogozha.tsichat.form;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthorizationFormModel {
    
    @NotEmpty(message="Это поле обязательно для заполнения")
    private String userLogin;
    
    @NotEmpty(message="Это поле обязательно для заполнения")
    private String userPassword;

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
}
