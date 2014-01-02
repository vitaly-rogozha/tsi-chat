package lv.rogozha.tsichat.domain;

public class User {

 private Long id;
 private String login;
 private String loginId;
 private String password;
 
    public User() {}
    
    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.loginId = login.toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
}
