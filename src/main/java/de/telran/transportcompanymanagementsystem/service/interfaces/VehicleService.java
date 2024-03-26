package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;

import java.util.List;

public interface VehicleService {
    Vehicle getVehicleById(String id);
    List<Vehicle> getAllVehicle();
    Vehicle getVehicleByCarNumber(String carNumber);
    Vehicle setVehicleByCarNumber(String carNumber, String newCarNumber);
    void deleteVehicleByCarNumber(String carNumber);
    void deleteVehicleById (String id);
    Vehicle create (Vehicle vehicle);
}
