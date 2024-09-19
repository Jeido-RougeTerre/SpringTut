package com.jeido.exercisespring.dto;

import com.jeido.exercisespring.entities.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoSend {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private int age;

    public static StudentDtoSend of(Student student) {
        return new StudentDtoSend(student.getId(), student.getName(), student.getSurname(), student.getEmail(), student.getAge());
    }
}
