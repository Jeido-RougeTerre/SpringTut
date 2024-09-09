package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.models.Student;
import com.jeido.exercisespring.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller("/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping("/student")
    public String index(Model model) {
        return search(null, model);
    }

    @RequestMapping("/student/inscription")
    public String inscription() {
        return "student/inscription";
    }

    @RequestMapping("/student/{id}")
    public String student(@PathVariable("id")UUID id, Model model) {
        Student student = studentService.getStudent(id);
        model.addAttribute("student", student);
        return "student/details";
    }

    @RequestMapping("/student/search")
    public String search(@PathVariable(value = "name", required = false)String name ,Model model) {
        if (name != null) {
            List<Student> students = studentService.getStudentsByName(name);
            model.addAttribute("students", students);
            model.addAttribute("searchedName", name);
            model.addAttribute("mode", "search");
        } else {
            model.addAttribute("mode", "list");
        }
        return "student/index";
    }
}
