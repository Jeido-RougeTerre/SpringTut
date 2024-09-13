package com.jeido.exercisespring.services;

import com.jeido.exercisespring.dao.UserRepository;
import com.jeido.exercisespring.entities.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    @Autowired
    private HttpSession session;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User register(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            return null;
        }
        return userRepository.save(User.builder().username(username).password(password).build());
    }

    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            session.setAttribute("username", null);
            session.setAttribute("isLoggedIn", false);
            return false;
        }




        if (password.equals(user.getPassword())) {
            session.setAttribute("username", user.getUsername());
            session.setAttribute("isLoggedIn", true);
            return true;
        }
        session.setAttribute("isLoggedIn", false);
        return false;
    }

    public boolean isLoggedIn() {
        try {
            return (boolean) session.getAttribute("isLoggedIn");
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        session.invalidate();
    }
}
