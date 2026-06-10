package com.lgcns.jpadsl.common;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class CardNoValidator implements ConstraintValidator<Telno, String> {
    private static final Pattern PATTERN = Pattern.compile(
            "\\d{10,19}"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null || value.isBlank()) return true;

        String normalized = value.replaceAll("[\\s-]", ""); // 공백, 하이픈 제거
        if (!PATTERN.matcher(normalized).matches())
            return false;

        return luhn(normalized);
    }

    // 세계표준 10 ~ 19 카드번호 체크 알고리즘(Luhn)
    private boolean luhn(String cardno) {
        int sum = 0;
        boolean alternate = false;

        for (int i = cardno.length() - 1; i >= 0; i--) {
            int digit = cardno.charAt(i) - '0';

            if (alternate) {
                digit *= 2;
                if (digit > 9)
                    digit -= 9;
            }

            sum += digit;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }

}
