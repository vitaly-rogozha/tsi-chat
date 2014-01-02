package lv.rogozha.tsichat.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import lv.rogozha.tsichat.domain.Message;
import lv.rogozha.tsichat.domain.User;

public class MessageServiceImpl implements MessageService {
    
    private List<Message> messages;
    private HashMap<User, Long> views;
    private ArrayList<String> commands;
    private ArrayList<String> stupidJokes;
    
    @Inject private UserService userService;
    @Inject private ActiveUserService activeUserService;
    
    MessageServiceImpl() {
        messages = new ArrayList<Message>();
        views = new HashMap<User, Long>();
        commands = new ArrayList<String>();
        commands.add("/joke");
        commands.add("/bye");
        commands.add("/hello");
        commands.add("/exit");
        stupidJokes = new ArrayList<String>();
        stupidJokes.add("Boy messages text his Girl \"Honey, I can't live without you! When you come to me?\"Here is the KILLING Reply -\"Who is dying! I lost my saved numbers, kindly tell me your name?\"");
        stupidJokes.add("I thing..Fear Factor would have been much scarier if it had just been people in their twenties trying to figure out how to have careers!");
        stupidJokes.add("Photographer: My secret of success is? 'Think negative'!");
        stupidJokes.add("Make no mistakes. The junior is your biggest prospect!");
        stupidJokes.add("Wife: If I would have been married to a Monster, I would have been felt much better than with you...\n" +
"Man: But marriages are not allowed in same blood relation!!");
        stupidJokes.add("I have already acted on your memo on saving power in my department by an immediate ban on employee empowerment!");
        stupidJokes.add("The surprising thing you can hear from a midget is 'Your hair smells good'.");
        stupidJokes.add("He asked me: How's the weather up there? and I replied: Its warm, how's it down there?");
        stupidJokes.add("I want you to continue sacking. I don't want people to believe that we've one soft.");
        stupidJokes.add("I want you to look at me the same way I look at pizza!");
    }
    
    @Override
    public void addMessage(Message message) {
        if (messages.size() >= 50) {
            for (int i=0; i<48; i++) {
                messages.add(i, messages.get(i+1));
            }
            messages.add(49, message);
        } else {
            messages.add(message);
        }
    }
    
    @Override
    public List<Message> getMessages(User user) {
        List<Message> messagesToReturn = new ArrayList<Message>();
        if (views.get(user) != null) {
            for (Message message: messages) {
                if (message.getTimestamp() > views.get(user)) {
                    messagesToReturn.add(message);
                }
            }
        } else {
            messagesToReturn = messages;
        }
        views.put(user, System.currentTimeMillis());
        return messagesToReturn;
    }
    
    @Override
    public void clearMessageHistoryOnLogin(User user) {
        views.put(user, System.currentTimeMillis()-1);
    }
    
    @Override
    public Boolean isCommand(Message message) {
        for (String command: commands) {
            if (command.equals(message.getTextMessage())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public void resolveCommand(Message message, HttpSession session) {
        String command = message.getTextMessage();
        
        if (command.equals(commands.get(0))) {
            int randomJokeIndex = 0 + (int)(Math.random()*9);
            String randomJokeText = stupidJokes.get(randomJokeIndex);
            
            User admin = new User();
            admin.setLogin("<font color='000000'>* Системное сообщение</font>");
            admin.setId(-1L);

            String messageText = "<font color='green'>Юзер '<i>" + message.getAuthor().getLogin() + "</i>' попытался смешно пошутить: </font><font color='brown'>" + randomJokeText + "</font> ";

            Message newMessage = new Message(admin, messageText, System.currentTimeMillis());

            addMessage(newMessage);
        } else if (command.equals(commands.get(1))) {
            User admin = new User();
            admin.setLogin("<font color='000000'>* Системное сообщение</font>");
            admin.setId(-1L);

            String messageText = "<font color='green'>Юзер '<i>" + message.getAuthor().getLogin() + "</i>' прощается с обитателями TSI chat'a</font>";

            Message newMessage = new Message(admin, messageText, System.currentTimeMillis());

            addMessage(newMessage);
        } else if (command.equals(commands.get(2))) {
            User admin = new User();
            admin.setLogin("<font color='000000'>* Системное сообщение</font>");
            admin.setId(-1L);

            String messageText = "<font color='green'>Юзер '<i>" + message.getAuthor().getLogin() + "</i>' с улыбкой на лице приветствует всех обитателей TSI chat'a</font>";

            Message newMessage = new Message(admin, messageText, System.currentTimeMillis());

            addMessage(newMessage);
        } else if (command.equals(commands.get(3))) {
            User currentUser = (User) session.getAttribute("user");
            session.removeAttribute("user");
            activeUserService.removeUser(currentUser);
            User admin = new User();
            admin.setLogin("<font color='000000'>* Системное сообщение</font>");
            admin.setId(-1L);

            String messageText = "<font color='green'>Юзер '<i>" + message.getAuthor().getLogin() + "</i>' вышел</font>";

            Message newMessage = new Message(admin, messageText, System.currentTimeMillis());
            addMessage(newMessage);
        }
    }
    
}
