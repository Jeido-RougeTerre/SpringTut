package com.jeido.exercisespring.services;

import com.jeido.exercisespring.entities.Status;
import com.jeido.exercisespring.entities.Todo;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Service
public class TodoService {
    private final List<Todo> allTodos = new ArrayList<>();

    public Todo createTodo(String name, String desc, Status status) {
        Todo todo = new Todo(name, desc, status);
        allTodos.add(todo);
        return todo;
    }

    public Todo getTodo(String name) {
        for (Todo t : allTodos) {
            if (t.getName().equals(name)) {
                return t;
            }
        }

        return null;
    }
}
