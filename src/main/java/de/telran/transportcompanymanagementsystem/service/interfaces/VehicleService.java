package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.CreateVehicleDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleDto;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleService {
    Vehicle getVehicleById(String id);
    List<Vehicle> getAllVehicle();
    Vehicle getVehicleByCarNumber(String carNumber);
    void deleteVehicleById (String id);
    List<Vehicle> getVehicleWithMaintenanceCostMoreOrEqual (BigDecimal maintenanceCost);
    VehicleDto create (CreateVehicleDto createVehicleDto);
}
