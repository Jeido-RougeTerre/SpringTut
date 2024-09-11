package com.jeido.exercisespring.models;

import com.jeido.exercisespring.validation.MailEduValid;
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
public class Student {
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
    @MailEduValid // ask the mail to be ended by .edu can be null
    private String email;

}
