package com.jeido.exercisespring.controllers;

import com.jeido.exercisespring.entities.Student;
import com.jeido.exercisespring.services.LoginService;
import com.jeido.exercisespring.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller("/student")
public class StudentController {

    private final StudentService studentService;

    private final LoginService loginService;


    @Autowired
    public StudentController(StudentService studentService, LoginService loginService) {
        this.studentService = studentService;
        this.loginService = loginService;
    }

    @RequestMapping("/student/register")
    public String register(Model model) {
        if (!loginService.isLoggedIn()) {
            return "redirect:/login";
        }
        Student student = Student.builder().name("").surname("").age(0).email("").build();
        model.addAttribute("student", student);
        model.addAttribute("action", "/student/register");
        model.addAttribute("mode", "add");
        return "student/details";
    }

    @PostMapping("/student/register")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (!loginService.isLoggedIn()) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("action", "/student/register");
            model.addAttribute("mode", "add");
            return "student/details";
        }

        Student savedStudent = studentService.save(student.getSurname(), student.getName(), student.getAge(), student.getEmail());
        model.addAttribute("student", savedStudent);
        model.addAttribute("action", "");
        model.addAttribute("mode", "info");
        return "redirect:/student/" + savedStudent.getId();
    }

    @RequestMapping("/student/{id}")
    public String student(@PathVariable("id")UUID id, Model model) {
        if (!loginService.isLoggedIn()) {
            return "redirect:/login";
        }
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("mode", "info");
        model.addAttribute("action", "");
        return "student/details";
    }

    @RequestMapping("/student")
    public String search(@RequestParam(name = "search", required = false)String search , Model model) {
        List<Student> students;
        if (search != null) {
            students = studentService.findByNameOrSurname(search);
            model.addAttribute("students", students);
            model.addAttribute("searchedName", search);
            model.addAttribute("mode", "search");
        } else {
            students = studentService.findAll();
            model.addAttribute("students", students);
            model.addAttribute("mode", "list");
        }
        return "student/index";
    }

    @PostMapping("/student/search")
    public String searchPost(@ModelAttribute("search")String name) {
        return "redirect:/student?search=" + name;
    }

    @GetMapping("/student/delete/{id}")
    public String deleteStudent(@PathVariable("id") UUID id) {
        if (!loginService.isLoggedIn()) {
            return "redirect:/login";
        }
        studentService.delete(id);
        return "redirect:/student";
    }

    @GetMapping("/student/edit/{id}")
    public String editStudent(@PathVariable("id") UUID id, Model model) {
        if (!loginService.isLoggedIn()) {
            return "redirect:/login";
        }
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("mode", "edit");
        model.addAttribute("action", "/student/edit/" + student.getId());
        return "student/details";
    }

    @PostMapping("/student/edit/{id}")
    public String studentEdit(@PathVariable("id") UUID id,@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model) {
        if (!loginService.isLoggedIn()) {
            return "redirect:/login";
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("mode", "edit");
            model.addAttribute("student", student);
            model.addAttribute("action", "/student/edit/" + student.getId());
            return "student/details";
        }
        Student updatedStudent = studentService.update(student);
        model.addAttribute("student", updatedStudent);
        model.addAttribute("mode", "info");
        model.addAttribute("action", "");
        return "redirect:/student/" + id;
    }

    @RequestMapping("/login")
    public String login() {
        return "login/form";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if (loginService.login(username, password)) {
            return "redirect:/student";
        }
        return "login/form";
    }

    @RequestMapping("/logout")
    public String logout() {
        loginService.logout();
        return "redirect:/student";
    }


}
