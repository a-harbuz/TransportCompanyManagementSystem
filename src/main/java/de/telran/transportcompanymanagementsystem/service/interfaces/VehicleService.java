package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;

public interface VehicleService {
    Vehicle getVehicleById(String id);
}
