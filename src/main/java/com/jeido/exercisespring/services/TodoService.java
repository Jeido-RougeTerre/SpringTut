package com.jeido.exercisespring.services;

import com.jeido.exercisespring.models.Status;
import com.jeido.exercisespring.models.Todo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TodoService {
    private final Map<UUID, Todo> allTodos = new HashMap<>();

    public Todo createTodo(String name, String desc, Status status) {
        UUID id = UUID.randomUUID();
        Todo todo = new Todo(id, name, desc, status);
        allTodos.put(id, todo);
        return todo;
    }

    public Todo getTodo(UUID id) {
        return allTodos.get(id);
    }

    public List<Todo> getAllTodos() {
        return allTodos.values().stream().toList();
    }
}
