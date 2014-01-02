package lv.rogozha.tsichat.controller;


import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import lv.rogozha.tsichat.domain.User;
import lv.rogozha.tsichat.form.AuthorizationFormModel;
import lv.rogozha.tsichat.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {
    
    @Inject private UserService userService;
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String mainPageAction(Model model) {
        AuthorizationFormModel authForm = new AuthorizationFormModel();
        model.addAttribute("authForm", authForm);
        return "main-page";
    }
    
    @RequestMapping(value="/", method=RequestMethod.POST)
    public String checkAuth(@Valid AuthorizationFormModel authForm, BindingResult result, HttpSession session, Model model) {
        
        List<String> loginErrors = new ArrayList<String>();
        List<String> passwordErrors = new ArrayList<String>();
        
        if (result.hasErrors()) {
            for (FieldError fieldError: result.getFieldErrors("userLogin")) {
                loginErrors.add(fieldError.getDefaultMessage());
            }
            
            for (FieldError fieldError: result.getFieldErrors("userPassword")) {
                passwordErrors.add(fieldError.getDefaultMessage());
            }
        }
        
        if ((loginErrors.size() + passwordErrors.size() == 0) && !userService.isAuthorizationValid(authForm.getUserLogin(), authForm.getUserPassword())) {
            loginErrors.add("Вы ввели неверный логин или пароль");
        }
        
        if (loginErrors.size() + passwordErrors.size() != 0) {
            model.addAttribute("loginErrors", loginErrors);
            model.addAttribute("passwordErrors", passwordErrors); 
            model.addAttribute("displayAuthForm", true);
            model.addAttribute("authForm", authForm);
            return "main-page";
        } else {
            User user = userService.getUserByLogin(authForm.getUserLogin());
            session.setAttribute("user", user);
            return "redirect:/chat";
        }
    }
    
}
