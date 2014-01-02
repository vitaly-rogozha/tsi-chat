package lv.rogozha.tsichat.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import lv.rogozha.tsichat.domain.User;

public interface ActiveUserService {
    
    public void deleteInactiveUsers();
    public void activateUser(HttpSession session);
    public List<String> getActiveUsers();
    public void removeUser(User user);
}
