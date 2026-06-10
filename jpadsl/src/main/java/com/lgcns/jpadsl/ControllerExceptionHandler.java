package com.lgcns.jpadsl;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerExceptionHandler {
    // @Valid 또는 @Validated(with @RequestBody) 오류!
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(MethodArgumentNotValidException e) {
        var message = e.getBindingResult().getFieldErrors()   // .getAllErrors()
                .stream().collect(
                        Collectors.toMap(FieldError::getField,
                                err -> Objects.toString(err.getDefaultMessage(), "Not Valid Value!"),
                                (existing, newValue) -> existing + ", " + newValue, LinkedHashMap::new));
        return ResponseEntity.badRequest().body(message);
    }

    // @Validated(with @RequestParam, @PathVariable) 오류!
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleViolation(ConstraintViolationException e) {
        var message = e.getConstraintViolations()
                .stream().collect(
                        Collectors.toMap(v -> v.getPropertyPath().toString(),
                                v -> Objects.toString(v.getMessage(), "Violation Value!"),
                                (existing, newValue) -> existing + ", " + newValue, LinkedHashMap::new));
        return ResponseEntity.badRequest().body(message);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleDefaultException(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
