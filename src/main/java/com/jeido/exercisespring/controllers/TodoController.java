package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.models.Status;
import com.jeido.exercisespring.models.Todo;
import com.jeido.exercisespring.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.UUID;

@Controller("/todo")
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

    @RequestMapping("/todo/{id}")
    public String getOneTodo(@PathVariable("id") UUID id, Model model) {
        Todo todo = service.getTodo(id);
        model.addAttribute("todo", todo);
        return "todo/detail";
    }

    @RequestMapping("/todo/list")
    @ResponseBody
    public List<Todo> getAllTodos() {
        return service.getAllTodos();
    }


    @RequestMapping("/todo")
    public String home(Model model) {
        model.addAttribute("todos", service.getAllTodos());
        return "todo/home";
    }
}
