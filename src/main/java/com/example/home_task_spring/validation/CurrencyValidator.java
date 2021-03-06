package com.example.home_task_spring.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class CurrencyValidator implements ConstraintValidator<Currency, String> {

    private static final Set<String> CURRENCY = Set.of("RUB", "EUR", "USD");

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return CURRENCY.contains(value);
    }
}
