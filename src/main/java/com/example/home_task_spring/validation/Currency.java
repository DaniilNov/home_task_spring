package com.example.home_task_spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = CurrencyValidator.class)
public @interface Currency {

    String message() default "Некорректная валюта!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
