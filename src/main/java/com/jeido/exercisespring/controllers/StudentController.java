package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.models.Student;
import com.jeido.exercisespring.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
        studentService.createStudent("Doe", "John", 33, "john.doe@lemail.com");
    }

    @RequestMapping("/student/register")
    public String register(Model model) {
        Student student = Student.builder().name("").surname("").age(0).email("").build();
        model.addAttribute("student", student);
        return "student/register";
    }

    @PostMapping("/student/register")
    public String addStudent(@ModelAttribute("student") Student student) {
        studentService.createStudent(student.getSurname(), student.getName(), student.getAge(), student.getEmail());
        return "redirect:/student";
    }

    @RequestMapping("/student/{id}")
    public String student(@PathVariable("id")UUID id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student/details";
    }

    @RequestMapping("/student")
    public String search(@RequestParam(name = "search", required = false)String search , Model model) {
        List<Student> students;
        if (search != null) {
            students = studentService.getStudentsByName(search);
            model.addAttribute("students", students);
            model.addAttribute("searchedName", search);
            model.addAttribute("mode", "search");
        } else {
            students = studentService.getAllStudents();
            model.addAttribute("students", students);
            model.addAttribute("mode", "list");
        }
        return "student/index";
    }

    @PostMapping("/student/search")
    public String searchPost(@ModelAttribute("search")String name) {
        return "redirect:/student?search=" + name;
    }


}
