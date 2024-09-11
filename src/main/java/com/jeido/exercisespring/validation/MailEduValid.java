package com.jeido.exercisespring.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = MailEduConstraintValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MailEduValid {
    String value() default ".edu";
    String message() default "Not a Edu email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
