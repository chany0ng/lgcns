package com.lgcns.jpadsl.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = EnumValueValidator.class)
public @interface EnumValue {
    Class<? extends Enum<?>> enumClass();

    String message() default "유효하지 않은 값입니다!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
