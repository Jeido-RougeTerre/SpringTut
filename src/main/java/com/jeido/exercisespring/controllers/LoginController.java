package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.entities.User;
import com.jeido.exercisespring.services.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @RequestMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", User.builder().username("").password("").build());
        model.addAttribute("mode", "reg");
        return "/login/form";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        loginService.register(user.getUsername(), user.getPassword());
        return "redirect:/";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", User.builder().username("").password("").build());
        model.addAttribute("mode", "log");
        return "/login/form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user) {
        if(loginService.login(user.getUsername(), user.getPassword())) {
            return "redirect:/";
        }
        return "redirect:/login";
    }
    @RequestMapping("/logout")
    public String logout() {
        loginService.logout();
        return "redirect:/";
    }
}
