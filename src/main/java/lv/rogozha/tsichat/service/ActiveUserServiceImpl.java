package lv.rogozha.tsichat.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import lv.rogozha.tsichat.domain.Message;
import lv.rogozha.tsichat.domain.User;
import org.springframework.scheduling.annotation.Scheduled;

public class ActiveUserServiceImpl implements ActiveUserService {
    
    ConcurrentHashMap<User, Long> activeUsers;
    @Inject private MessageService messageService;
    @Inject private UserService userService;
    
    ActiveUserServiceImpl() {
        activeUsers = new ConcurrentHashMap<User, Long>();
    }
    
    @Override
    @Scheduled(fixedRate=2500)
    public void deleteInactiveUsers() {
        for(Entry<User, Long> entry : activeUsers.entrySet()) {
            User user = entry.getKey();
            Long time = entry.getValue();
            
            if (System.currentTimeMillis() - time > 7000) {
                activeUsers.remove(user);
                System.out.println("User " + user.getLogin() + " was kicked due timeout!");
                
                User admin = new User();
                admin.setLogin("<font color='000000'>* Системное сообщение</font>");
                admin.setId(-1L);

                String messageText = "<font color='green'>Юзер '<i>" + user.getLogin() + "</i>' покидает нас (kicked due timeout)</font>";

                Message message = new Message(admin, messageText, System.currentTimeMillis());
              
                messageService.addMessage(message);
            }   
        }
    }
    
    @Override
    public void activateUser(HttpSession session) {
        User currentUser = userService.getCurrentUser(session);
        Long currentTime = System.currentTimeMillis();
        
        if (!isAlreadyActive(currentUser)) {
            User admin = new User();
            admin.setLogin("<font color='000000'>* Системное сообщение</font>");
            admin.setId(-1L);

            String messageText = "<font color='green'>К нам приходит юзер '<i>" + currentUser.getLogin() + "</i>'! Поприветствуем же его :)</font>";

            Message message = new Message(admin, messageText, System.currentTimeMillis());
            
            messageService.clearMessageHistoryOnLogin(currentUser); 
            messageService.addMessage(message);
        }

        activeUsers.put(currentUser, currentTime);
    }
    
    @Override
    public List<String> getActiveUsers() {
        ArrayList<String> activeLogins = new ArrayList<String>();
        for(Entry<User, Long> entry : activeUsers.entrySet()) {
            User user = entry.getKey();
            activeLogins.add(user.getLogin());
        }
        return activeLogins;
    }
    
    @Override
    public void removeUser(User user) {
        for(Entry<User, Long> entry : activeUsers.entrySet()) {
            User currentUser = entry.getKey();
            if (currentUser.getId() == user.getId()) {
                activeUsers.remove(user);
            }
        }
    }
    
    private Boolean isAlreadyActive(User user) {
        for(Entry<User, Long> entry : activeUsers.entrySet()) {
            User entryUser = entry.getKey();
            if (entryUser.getId() == user.getId()) {
                return true;
            } 
        }
        return false;
    }
    
}
