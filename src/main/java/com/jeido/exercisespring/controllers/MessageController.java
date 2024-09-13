package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.dao.UserRepository;
import com.jeido.exercisespring.entities.Message;
import com.jeido.exercisespring.services.LoginService;
import com.jeido.exercisespring.services.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
public class MessageController {
    private final MessageService messageService;
    private final LoginService loginService;
    private final UserRepository userRepository;

    private final HttpSession session;


    @Autowired
    public MessageController(MessageService messageService, LoginService loginService, UserRepository userRepository, HttpSession session) {
        this.messageService = messageService;
        this.loginService = loginService;
        this.userRepository = userRepository;
        this.session = session;
    }

    @RequestMapping("/forum")
    public String forum(Model model) {
        model.addAttribute("logged", loginService.isLoggedIn());
        model.addAttribute("messages", messageService.findAll());
        Message msg = new Message();
        if (loginService.isLoggedIn()) {
            msg.setUser(userRepository.findByUsername(session.getAttribute("username").toString()));
        }
        model.addAttribute("message", msg);
        return "forum/forumList";
    }

    @PostMapping("/forum")
    public String forum(@RequestParam("username")String username, @RequestParam("content")String content) {
        if (!loginService.isLoggedIn()) {
            return "redirect:/login";
        }

        messageService.save(Message.builder().user(userRepository.findByUsername(username)).content(content).build());

        return "redirect:/forum";
    }

}
