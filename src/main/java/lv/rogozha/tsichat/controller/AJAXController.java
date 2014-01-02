package lv.rogozha.tsichat.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lv.rogozha.tsichat.domain.AJAXMessageResponse;
import lv.rogozha.tsichat.domain.Message;
import lv.rogozha.tsichat.service.UserService;
import lv.rogozha.tsichat.domain.User;
import lv.rogozha.tsichat.form.RegistrationFormModel;
import lv.rogozha.tsichat.service.ActiveUserService;
import lv.rogozha.tsichat.service.MessageService;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AJAXController {
    
    @Inject UserService userService;
    @Inject MessageService messageService;
    @Inject ActiveUserService activeUserService;
    
    @RequestMapping(value="/createuser/", method=RequestMethod.POST)
    public @ResponseBody HashMap createUser(@Valid RegistrationFormModel regForm, BindingResult result) {
        
        HashMap<String, Object> response = new HashMap<String, Object>();
        
        ArrayList<String> loginErrors = new ArrayList<String>();
        ArrayList<String> passwordErrors = new ArrayList<String>();
        ArrayList<String> passwordRepeatErrors = new ArrayList<String>();
        
        List<FieldError> resultLoginErrors = result.getFieldErrors("userLogin");
        List<FieldError> resultPasswordErrors = result.getFieldErrors("userPassword");
        List<FieldError> resultPasswordRepeatErrors = result.getFieldErrors("userPasswordRepeat");
        
        if (userService.userExists(regForm.getUserLogin())) {
            loginErrors.add("Пользователь с таким логином уже существует");
        }
        
        // Dear Lord, please forgive me for this:
        
        for (FieldError fieldError: resultLoginErrors) {
            loginErrors.add(fieldError.getDefaultMessage());
        }

        for (FieldError fieldError: resultPasswordErrors) {
            passwordErrors.add(fieldError.getDefaultMessage());
        }

        for (FieldError fieldError: resultPasswordRepeatErrors) {
            passwordRepeatErrors.add(fieldError.getDefaultMessage());
        }
        
        if (!regForm.getUserPassword().equals(regForm.getUserPasswordRepeat())) {
            passwordRepeatErrors.add("Пароли не совпадают");
        }
        
        // Amen..
        
        response.put("loginErrors", loginErrors);
        response.put("passwordErrors", passwordErrors);
        response.put("passwordRepeatErrors", passwordRepeatErrors);
        
        if (loginErrors.size() + passwordErrors.size() + passwordRepeatErrors.size() != 0) {
            response.put("success", false);
        } else {
            response.put("success", true);
            User user = new User(regForm.getUserLogin(), regForm.getUserPassword());
            userService.addUser(user);
        }
        
        return response;
    }
    
    @RequestMapping(value="/sendmessage", method=RequestMethod.POST)
    public @ResponseBody String sendMessage(HttpServletRequest request, HttpSession session) {
     
        if (userService.isAuthorized(session)) {
            String messageText = request.getParameter("message");
            Long timestamp = System.currentTimeMillis();
            User author = userService.getCurrentUser(session);
            
            Message message = new Message(author, messageText, timestamp);
            
            if (messageService.isCommand(message)) {
                messageService.resolveCommand(message, session);
            } else {
                messageService.addMessage(message);
            }
        }
        return null;
    }
    
    @RequestMapping(value="/getmessages", method=RequestMethod.POST)
    public @ResponseBody ArrayList<AJAXMessageResponse> getMessages(HttpSession session) {
        
        if (!userService.isAuthorized(session)) {
            return null;
        }
        
        ArrayList<AJAXMessageResponse> result = new ArrayList<AJAXMessageResponse>();
        
        User currentUser = userService.getCurrentUser(session);
        List<Message> userMessages = messageService.getMessages(currentUser);
        
        for (Message userMessage: userMessages) {
            AJAXMessageResponse ajaxMessageResponse = new AJAXMessageResponse();
            
            String author = userMessage.getAuthor().getLogin();
            String message = userMessage.getTextMessage();
            
            if (userMessage.getAuthor().getId() != -1L) {
                author = StringEscapeUtils.escapeHtml(author);
                message = StringEscapeUtils.escapeHtml(message);
            }
            
            ajaxMessageResponse.setAuthor(author);
            ajaxMessageResponse.setMessage(message);
            
            Long timestamp = userMessage.getTimestamp();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date(timestamp);
            
            ajaxMessageResponse.setTime(simpleDateFormat.format(date));
            
            result.add(ajaxMessageResponse);
        }
        return result;
    }
    
    @RequestMapping(value="/activateuser", method=RequestMethod.POST)
    public @ResponseBody List activateUser(HttpSession session) {
        if (!userService.isAuthorized(session)) {
            return null;
        }
        activeUserService.activateUser(session);
        List<String> activeUsers = activeUserService.getActiveUsers();
        return activeUsers;
    }
}
