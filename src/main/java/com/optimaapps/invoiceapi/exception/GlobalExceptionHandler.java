package com.optimaapps.invoiceapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerNotFound(CustomerNotFoundException ex) {
        log.error("CustomerNotFoundException: {}", ex.getMessage(), ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", HttpStatus.NOT_FOUND.toString());
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvoiceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleInvoiceNotFound(InvoiceNotFoundException ex) {
        log.error("InvoiceNotFoundException: {}", ex.getMessage(), ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", HttpStatus.NOT_FOUND.toString());
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleProductNotFound(ProductNotFoundException ex) {
        log.error("ProductNotFoundException: {}", ex.getMessage(), ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", HttpStatus.NOT_FOUND.toString());
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(InvoiceItemNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleInvoiceItemNotFound(InvoiceItemNotFoundException ex) {
        log.error("InvoiceItemNotFoundException: {}", ex.getMessage(), ex);
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", HttpStatus.NOT_FOUND.toString());
        errorResponse.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        log.warn("Validation error: {}", ex.getMessage(), ex);
        Map<String, Object> errors = new HashMap<>();
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        errors.put("error", "Greška prilikom validacije");

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            String message;
            switch (Objects.requireNonNull(fieldError.getCode())) {
                case "DecimalMin":
                    message = "Polje " + fieldError.getField() + " mora biti veće od 0.00";
                    break;
                case "NotNull":
                    message = "Polje " + fieldError.getField() + " ne može biti null";
                    break;
                case "NotBlank":
                    message = "Polje " + fieldError.getField() + " ne može biti blank";
                    break;
                case "Size":
                    message = fieldError.getDefaultMessage();
                    break;
                case "Min":
                    message = "Polje " + fieldError.getField() + " mora biti veće od 0";
                    break;
                default:
                    message = "Nevalidna vrednost uneta za jedno do polja, obratite se administratoru.";
            }
            errors.put("message", message);
            log.warn("Validation failed for field '{}': {}", fieldError.getField(), message);
        }
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
