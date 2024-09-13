package com.jeido.exercisespring.services;

import com.jeido.exercisespring.dao.StudentRepository;
import com.jeido.exercisespring.entities.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(String lastName, String firstName, int age, String email, String imgPath) {
        return studentRepository.save(Student.builder().surname(lastName).name(firstName).age(age).email(email).imgPath(imgPath).build());
    }

    public Student save(String lastName, String firstName, int age, String email) {
        return studentRepository.save(Student.builder().surname(lastName).name(firstName).age(age).email(email).build());
    }

    public Student findById(UUID id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public List<Student> findByNameOrSurname(String search) {
        return studentRepository.findByNameIgnoreCaseOrSurnameIgnoreCase(search, search);
    }

    public void delete(UUID id) {
        Student student = findById(id);
        if (student == null) return;
        studentRepository.delete(student);
    }

    public Student update(Student student) {
        return studentRepository.save(student);
    }
}
