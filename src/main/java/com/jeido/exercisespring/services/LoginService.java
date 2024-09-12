package com.jeido.exercisespring.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private HttpSession session;

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    public boolean login(String username, String password) {
        if (username.equals(USERNAME) && password.equals(PASSWORD)) {
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
