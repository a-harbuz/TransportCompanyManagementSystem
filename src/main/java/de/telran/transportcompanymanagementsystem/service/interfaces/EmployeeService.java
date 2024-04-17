package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.EmployeeAfterRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(String id);
    List<Employee> getEmployeeList();
    List<Employee> getDriverList();
    EmployeeAfterRegistrationDto createEmployee (EmployeeRegistrationDto employeeRegistrationDto);
    List<EmployeeWithVehicleAndMaintenanceDto> getEmployeeWithOneOrMoreVehicleMaintenance();

}
