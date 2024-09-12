package com.jeido.exercisespring.dao;

import com.jeido.exercisespring.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<Student, UUID> {
    List<Student> findByNameIgnoreCaseOrSurnameIgnoreCase(String name, String surname);
}
