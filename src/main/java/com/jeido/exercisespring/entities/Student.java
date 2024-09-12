package com.jeido.exercisespring.entities;

import com.jeido.exercisespring.validation.MailEduValid;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotNull
    @Size(min = 2, max = 50, message = "Minimum 2 characters")
    private String name;
    @NotNull
    @Size(min = 2, max = 50, message = "Minimum 2 characters")
    private String surname;
    @NotNull
    @Min(value = 10, message = "Should be higher than 9")
    @Max(value = 99, message = "Should be Lower than 100")
    private int age;
    @Email
//    @MailEduValid // ask the mail to be ended by .edu can be null or blank
    private String email;

}
