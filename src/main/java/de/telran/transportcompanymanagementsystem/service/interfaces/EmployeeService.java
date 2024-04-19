package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.EmployeeAfterRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto getEmployeeById(String id);
    List<EmployeeDto> getEmployeeList();
    List<EmployeeDto> getDriverList();
    EmployeeAfterRegistrationDto createEmployee (EmployeeRegistrationDto employeeRegistrationDto);
    List<EmployeeWithVehicleAndMaintenanceDto> getEmployeeWithOneOrMoreVehicleMaintenance();

}
