package com.jeido.exercisespring.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class Todo {
    private UUID id;
    private String name;
    private String description;
    private Status status;

}
