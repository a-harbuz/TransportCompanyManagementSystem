package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Maintenance;

import java.math.BigDecimal;
import java.util.List;

public interface MaintenanceService {
    Maintenance getMaintenanceById(String id);
    List<Maintenance> getMaintenanceCostMoreThan (BigDecimal maintenanceCost);
}
