package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.entities.Status;
import com.jeido.exercisespring.entities.Todo;
import com.jeido.exercisespring.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TodoController {
    private final TodoService service;

    @Autowired
    public TodoController(TodoService todoService) {
        this.service = todoService;
        service.createTodo(
                "macarons",
                "Manger le plus de macarons possible...",
                Status.IN_PROGRESS
        );
        service.createTodo(
                "formation",
                "Suivre sa formation",
                Status.IN_PROGRESS
        );
        service.createTodo(
                "Plan Machiavélique",
                "Conquérir le monde",
                Status.NOT_STARTED
        );
        service.createTodo(
                "dessin",
                "Déssiner une reproduction parfaite de Mona Lisa",
                Status.FAILED
        );
    }

    @RequestMapping("one")
    public String getOneTodo(Model model) {
        Todo t = service.getTodo("macarons");
        model.addAttribute("name", t.getName());
        model.addAttribute("description", t.getDescription());
        model.addAttribute("status", t.getStatus().name());
        return "todo/detail";
    }

    @RequestMapping("all")
    @ResponseBody
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }

    @RequestMapping("/")
    public String index(Model model) {
        return home(model);
    }

    @RequestMapping("home")
    public String home(Model model) {
        model.addAttribute("todos", service.getAllTodos());
        return "todo/home";
    }
}
