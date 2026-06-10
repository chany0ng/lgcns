package com.lgcns.jpadsl.common;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = TelnoValidator.class)
public @interface Telno {
    String message() default "유효하지 않은 전화번호 형식입니다!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
