package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.MaintenanceDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleWithMaintenanceDto;

import java.math.BigDecimal;
import java.util.List;

public interface MaintenanceService {
    MaintenanceDto getMaintenanceById(String id);
    List<MaintenanceDto> getMaintenanceCostMoreThan (BigDecimal maintenanceCost);
    List<VehicleWithMaintenanceDto> getVehiclesWithMaintenance();
}
