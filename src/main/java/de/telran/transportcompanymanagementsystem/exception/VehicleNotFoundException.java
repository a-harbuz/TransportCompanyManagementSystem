package de.telran.transportcompanymanagementsystem.exception;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String message) {
        super(message);
    }
}
