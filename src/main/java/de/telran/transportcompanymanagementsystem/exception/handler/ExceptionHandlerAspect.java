package de.telran.transportcompanymanagementsystem.exception.handler;

import de.telran.transportcompanymanagementsystem.exception.CompanyNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.TaskNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.VehicleNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;


@RestControllerAdvice
public class ExceptionHandlerAspect {
    @ExceptionHandler(VehicleNotFoundException.class)
    public ResponseEntity<String> handleVehicleNotFoundException(VehicleNotFoundException ex) {
        return getResponse(ex);
    }

    @ExceptionHandler(CompanyNotFoundException.class)
    public ResponseEntity<String> handleCompanyNotFoundException(CompanyNotFoundException ex) {
        return getResponse(ex);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException ex) {
        return getResponse(ex);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<String> handleDataNotFoundException(DataNotFoundException ex) {
        return getResponse(ex);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .headers(headers)
                .body("!!!! Illegal argument");
    }

    public ResponseEntity<String> getResponse(Exception ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .headers(headers)
                .body("!!!! " + ex.getMessage());
    }
}
