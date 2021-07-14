package com.avansas.avansascase.exceptionhandler;

import com.avansas.avansascase.exception.EmailExistException;
import com.avansas.avansascase.exception.InvalidBirthDateException;
import com.avansas.avansascase.exception.PhoneNumberExistException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException exceptions) {
        Map<String, String> validationErrors = new HashMap<>();

        exceptions.getBindingResult().getFieldErrors()
                .forEach(fieldError -> validationErrors
                        .put(fieldError.getField(), fieldError.getDefaultMessage()));

        return ResponseEntity.badRequest().body(validationErrors);
    }

    @ExceptionHandler(PhoneNumberExistException.class)
    public ResponseEntity<Map<String, String>> handlePhoneNumberException(PhoneNumberExistException exception) {
        return ResponseEntity.badRequest().body(Collections.singletonMap("phoneNumber", exception.getMessage()));
    }

    @ExceptionHandler(EmailExistException.class)
    public ResponseEntity<Map<String, String>> handleEmailException(EmailExistException exception) {
        return ResponseEntity.badRequest().body(Collections.singletonMap("email", exception.getMessage()));
    }

    @ExceptionHandler(InvalidBirthDateException .class)
    public ResponseEntity<Map<String, String>> handleDateException(InvalidBirthDateException exception) {
        return ResponseEntity.badRequest().body(Collections.singletonMap("birthDate", exception.getMessage()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleDateException(HttpMessageNotReadableException exception) {
        return ResponseEntity.badRequest().body(Collections.singletonMap("birthDate", "Date of Birth is invalid."));
    }
}