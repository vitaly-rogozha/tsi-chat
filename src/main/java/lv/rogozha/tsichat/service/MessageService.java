package lv.rogozha.tsichat.service;

import java.util.List;
import javax.servlet.http.HttpSession;
import lv.rogozha.tsichat.domain.Message;
import lv.rogozha.tsichat.domain.User;

public interface MessageService {
    
    public void addMessage(Message message);
    public List<Message> getMessages(User user);
    public void clearMessageHistoryOnLogin(User user);
    public Boolean isCommand(Message message);
    public void resolveCommand(Message message, HttpSession session);
    
}
