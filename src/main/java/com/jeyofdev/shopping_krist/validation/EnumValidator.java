package com.jeyofdev.shopping_krist.validation;

import com.jeyofdev.shopping_krist.annotation.ValidEnum;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValidator implements ConstraintValidator<ValidEnum, Enum<?>> {
    private ValidEnum annotation;

    @Override
    public void initialize(ValidEnum constraintAnnotation) {
        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(Enum<?> value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        Object[] enumValues = this.annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (value.equals(enumValue)) {
                    return true;
                }
            }
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(annotation.message()).addConstraintViolation();
        return false;
    }
}