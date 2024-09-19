package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.dto.StudentDtoReceive;
import com.jeido.exercisespring.dto.StudentDtoSend;
import com.jeido.exercisespring.entities.Student;
import com.jeido.exercisespring.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/student/")
public class StudentApiController {
    private final StudentService studentService;

    @Autowired
    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDtoSend>> getStudents() {
        List<Student> students = studentService.findAll();
        List<StudentDtoSend> send = new ArrayList<>();
        for (Student student : students) {
            send.add(StudentDtoSend.of(student));
        }

        return ResponseEntity.ok(send);
    }

    @GetMapping("{id}")
    public ResponseEntity<StudentDtoSend> getStudentById(@PathVariable("id")UUID id) {
        Student student = studentService.findById(id);
        StudentDtoSend studentDtoSend = StudentDtoSend.of(student);
        return ResponseEntity.ok(studentDtoSend);
    }

    @PostMapping
    public ResponseEntity<StudentDtoSend> createStudent(@Validated @RequestBody StudentDtoReceive studentDtoReceive) {
        Student student = studentService.save(studentDtoReceive);
        StudentDtoSend studentDtoSend = StudentDtoSend.of(student);
        return new ResponseEntity<>(studentDtoSend, HttpStatus.CREATED);
    }
}
