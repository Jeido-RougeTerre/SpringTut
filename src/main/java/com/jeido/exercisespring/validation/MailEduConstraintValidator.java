package com.jeido.exercisespring.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MailEduConstraintValidator implements ConstraintValidator<MailEduValid, String> {

    private String endMail;

    @Override
    public void initialize(MailEduValid constraintAnnotation) {
        endMail = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        boolean isValid = false;


        isValid = s.endsWith(endMail);


        return isValid;
    }
}
