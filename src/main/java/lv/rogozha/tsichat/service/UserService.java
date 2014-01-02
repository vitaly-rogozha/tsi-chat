package lv.rogozha.tsichat.service;

import javax.servlet.http.HttpSession;
import lv.rogozha.tsichat.domain.User;


public interface UserService {
    
    public void addUser(User user);
    public Boolean userExists(String login);
    public Boolean isAuthorizationValid(String login, String password);
    public User getUserByLogin(String login);
    public Boolean isAuthorized(HttpSession session);
    public User getCurrentUser(HttpSession session);
    
}
