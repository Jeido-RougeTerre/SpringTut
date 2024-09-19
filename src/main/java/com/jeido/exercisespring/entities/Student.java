package com.jeido.exercisespring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "student_id")
    private UUID id;
    private String name;
    private String surname;
    private int age;
    private String email;

    private String imgPath;

}
