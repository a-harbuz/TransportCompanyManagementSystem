package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Maintenance;

public interface MaintenanceService {
    Maintenance getMaintenanceById(String id);
}
