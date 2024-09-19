package com.jeido.exercisespring.dao;

import com.jeido.exercisespring.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StudentRepository extends CrudRepository<Student, UUID> {
    List<Student> findByNameLikeIgnoreCaseOrSurnameLikeIgnoreCase(String name, String surname);
}
