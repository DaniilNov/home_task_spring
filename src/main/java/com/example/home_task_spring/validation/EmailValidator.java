package com.example.home_task_spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final String AT = "@";
    private static final String DOT = ".";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value.contains(AT) & value.contains(DOT);
    }
}
