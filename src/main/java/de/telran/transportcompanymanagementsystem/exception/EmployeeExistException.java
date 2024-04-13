package de.telran.transportcompanymanagementsystem.exception;

public class EmployeeExistException extends RuntimeException {
    public EmployeeExistException(String message) {
        super(message);
    }
}
