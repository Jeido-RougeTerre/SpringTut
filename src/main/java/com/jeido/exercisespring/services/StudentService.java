package com.jeido.exercisespring.services;

import com.jeido.exercisespring.dao.StudentRepository;
import com.jeido.exercisespring.dto.StudentDtoReceive;
import com.jeido.exercisespring.entities.Student;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(StudentDtoReceive studentDtoReceive) {
        Student student = Student.builder()
                .name(studentDtoReceive.getName())
                .surname(studentDtoReceive.getSurname())
                .email(studentDtoReceive.getEmail())
                .age(studentDtoReceive.getAge())
                .build();
        return studentRepository.save(student);
    }

    public Student save(StudentDtoReceive studentDtoReceive, String imgPath) {
        Student student = Student.builder()
                .name(studentDtoReceive.getName())
                .surname(studentDtoReceive.getSurname())
                .email(studentDtoReceive.getEmail())
                .age(studentDtoReceive.getAge())
                .imgPath(imgPath)
                .build();
        return studentRepository.save(student);
    }

    public Student findById(UUID id) {
        return studentRepository.findById(id).orElseThrow();
    }

    public List<Student> findAll() {
        return (List<Student>) studentRepository.findAll();
    }

    public List<Student> findByNameOrSurname(String search) {
        return studentRepository.findByNameLikeIgnoreCaseOrSurnameLikeIgnoreCase(search, search);
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
