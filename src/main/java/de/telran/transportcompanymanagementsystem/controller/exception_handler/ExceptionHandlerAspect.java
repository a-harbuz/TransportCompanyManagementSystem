package de.telran.transportcompanymanagementsystem.controller.exception_handler;

import de.telran.transportcompanymanagementsystem.exception.VehicleNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAspect {
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleAuthorNotFoundException(VehicleNotFoundException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body("!!!!" + ex.getMessage());
    }
}
