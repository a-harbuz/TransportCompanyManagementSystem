package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle getVehicleById(String id);
    List<Vehicle> getAllVehicle();
}
