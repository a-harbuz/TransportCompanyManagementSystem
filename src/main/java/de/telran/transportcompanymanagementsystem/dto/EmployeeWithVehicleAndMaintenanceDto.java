package de.telran.transportcompanymanagementsystem.dto;

import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class EmployeeWithVehicleAndMaintenanceDto {
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private boolean isDriver;
    private boolean isWorking;
    private List<List<Maintenance>> maintenanceList;
}
