package de.telran.transportcompanymanagementsystem.exception.handler;

import de.telran.transportcompanymanagementsystem.exception.CompanyNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.TaskNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.VehicleNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAspect {
    @ExceptionHandler({VehicleNotFoundException.class, CompanyNotFoundException.class, TaskNotFoundException.class,
            DataNotFoundException.class})
    public ResponseEntity<String> handleException(Exception ex) {
        return getResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException() {
        return getResponse(HttpStatus.BAD_REQUEST, "Illegal argument");
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
