package com.jeyofdev.shopping_krist.annotation;

import com.jeyofdev.shopping_krist.validation.DarkModeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DarkModeValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDarkMode {
    String message() default "Invalid appearance value. Must be 'light' or 'dark'.";

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
