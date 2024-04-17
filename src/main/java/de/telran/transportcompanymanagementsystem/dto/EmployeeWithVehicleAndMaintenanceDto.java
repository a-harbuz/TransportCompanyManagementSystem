package de.telran.transportcompanymanagementsystem.dto;

import lombok.Data;

import java.util.UUID;
@Data
public class EmployeeWithVehicleAndMaintenanceDto {
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private boolean isDriver;
    private boolean isWorking;
}
