package com.jeyofdev.shopping_krist.validation;

import com.jeyofdev.shopping_krist.annotation.ValidDarkMode;
import com.jeyofdev.shopping_krist.core.enums.DarkMode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DarkModeValidator implements ConstraintValidator<ValidDarkMode, DarkMode> {
    private String message;

    @Override
    public void initialize(ValidDarkMode constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(DarkMode value, ConstraintValidatorContext context) {
        if ((value != DarkMode.LIGHT && value != DarkMode.DARK)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }
        return true;
    }
}