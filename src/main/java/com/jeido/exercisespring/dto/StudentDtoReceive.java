package com.jeido.exercisespring.dto;

import com.jeido.exercisespring.entities.Student;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoReceive {
    @Size(min = 3, max = 20)
    private String name;
    @Size(min = 3, max = 20)
    private String surname;
    @Min(10)
    @Max(100)
    private int age;
    @Email
    @Size(max = 255)
    private String email;

    private String imgPath;

    public static StudentDtoReceive of(Student student) {
        return new StudentDtoReceive(student.getName(), student.getSurname(), student.getAge(), student.getEmail(), student.getImgPath());
    }
}
