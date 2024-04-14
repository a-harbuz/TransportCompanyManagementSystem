package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.CreateVehicleDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleDto;
import java.math.BigDecimal;
import java.util.List;

public interface VehicleService {
    VehicleDto getVehicleById(String id);
    List<VehicleDto> getAllVehicle();
    VehicleDto getVehicleByCarNumber(String carNumber);
    void deleteVehicleById (String id);
    List<VehicleDto> getVehicleWithMaintenanceCostMoreOrEqual (BigDecimal maintenanceCost);
    VehicleDto create (CreateVehicleDto createVehicleDto);
}
