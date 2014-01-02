package lv.rogozha.tsichat.service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.http.HttpSession;
import lv.rogozha.tsichat.HibernateUtil;
import lv.rogozha.tsichat.domain.User;
import org.hibernate.Session;

public class UserServiceImpl implements UserService {
    
    @Override
    public void addUser(User user) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        user.setPassword(codePassword(user.getPassword()));
        
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public Boolean userExists(String login) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        String loginId = login.toLowerCase();
        
        session.beginTransaction();
        List users = session.createQuery("from User u where u.loginId = :loginId")
                .setParameter("loginId", loginId).list();
        session.getTransaction().commit();
        
        if (users.isEmpty()) {
            return false;
        }
        return true;
    }
    
    private String codePassword(String password) {
        String codedPassword = "";
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(password.getBytes(), 0, password.length());
            codedPassword = new BigInteger(1, md5.digest()).toString(16);
            
        } catch(NoSuchAlgorithmException ex) {
            // Smile :D
        }
        return codedPassword;
    }
    
    @Override
    public Boolean isAuthorizationValid(String login, String password) {  
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        String loginId = login.toLowerCase();
        String codedPassword = codePassword(password);
        
        session.beginTransaction();
        
        List users = session.createQuery("from User u where u.loginId = :loginId AND u.password = :password")
                .setParameter("loginId", loginId)
                .setParameter("password", codedPassword)
                .list();
        
        session.getTransaction().commit();
        
        if (users.isEmpty()) {
            return false;
        }
        return true;
    }
    
    @Override
    public User getUserByLogin(String login) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        
        String loginId = login.toLowerCase();
        
        session.beginTransaction();
        
        List users = session.createQuery("from User u where u.loginId = :loginId")
                .setParameter("loginId", loginId)
                .list();
        
        session.getTransaction().commit();
        
        User user = (User) users.get(0);
        
        return user;
    }
    
    @Override
    public Boolean isAuthorized(HttpSession session) {
        if (session.getAttribute("user") != null) {
            return true;
        }
        return false;
    }
    
    @Override
    public User getCurrentUser(HttpSession session) {
        User user = new User();
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");
        }
        return user;
    }

}
