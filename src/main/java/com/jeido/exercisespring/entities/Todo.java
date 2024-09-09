package com.jeido.exercisespring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Todo {
    private String name;
    private String description;
    private Status status;

}
