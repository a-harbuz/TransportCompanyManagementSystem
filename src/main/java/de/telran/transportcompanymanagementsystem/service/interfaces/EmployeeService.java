package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.*;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeById(String id);
    List<EmployeeAllDto> getEmployeeList();
    List<EmployeeDto> getDriverList();
    EmployeeAfterRegistrationDto createEmployee (EmployeeRegistrationDto employeeRegistrationDto);
    List<EmployeeWithVehicleAndMaintenanceDto> getEmployeeWithOneOrMoreVehicleMaintenance();

}
