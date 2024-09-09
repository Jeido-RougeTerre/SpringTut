package com.jeido.exercisespring.services;

import com.jeido.exercisespring.models.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final Map<UUID, Student> students;

    public StudentService() {
        students = new HashMap<>();
    }

    public void createStudent(String lastName, String firstName, int age,String email) {
        UUID id = UUID.randomUUID();
        students.put(id, new Student(id, lastName, firstName, age, email));
    }

    public Student getStudent(UUID id) {
        return students.get(id);
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public List<Student> getStudentsByName(String name) {
        return students.values().stream().filter(student ->
                student.getSurname().toLowerCase().concat(" " + student.getName().toLowerCase()).contains(name.toLowerCase())).toList();
    }
}
