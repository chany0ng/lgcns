package com.lgcns.jpadsl.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Stream;

public class EnumValueValidator implements ConstraintValidator<EnumValue, String> {
    private List<String> acceptableValues;

    @Override
    public void initialize(EnumValue annotation) {
        this.acceptableValues = Stream.of(annotation.enumClass().getEnumConstants()).map(Enum::name).toList();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("EnumValue.value = " + value + ", " + acceptableValues);
        if (value == null || value.isBlank()) return true;

        return this.acceptableValues.contains(value);
    }

}
