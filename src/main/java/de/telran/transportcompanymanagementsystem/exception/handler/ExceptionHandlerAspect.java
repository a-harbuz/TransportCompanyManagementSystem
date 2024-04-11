package de.telran.transportcompanymanagementsystem.exception.handler;

import de.telran.transportcompanymanagementsystem.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAspect {
    @ExceptionHandler({VehicleNotFoundException.class, CompanyNotFoundException.class, TaskNotFoundException.class,
            ContractNotFoundException.class, DataNotFoundException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        return getResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException() {
        return getResponse(HttpStatus.BAD_REQUEST, "Illegal argument");
    }

    @ExceptionHandler({ConstraintViolationException.class, CompanyBadRequestException.class})
    public ResponseEntity<String> handleConstraintViolationException(Exception ex) {
        return getResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    public ResponseEntity<String> getResponse(HttpStatus status, String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");
        return ResponseEntity
                .status(status)
                .headers(headers)
                .body("{\"message\": \"!!!! " + message + "\"}");
    }
}
