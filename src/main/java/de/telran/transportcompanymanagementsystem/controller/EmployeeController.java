package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.dto.*;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import de.telran.transportcompanymanagementsystem.annotation.employee.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetEmployeeByIdDocumentation(path="/{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id") String id) {
        //http://localhost:8080/employee/a060e1b8-7c07-4619-85dc-32c6a5afa269
        return employeeService.getEmployeeById(id);
    }

    @GetEmployeeListDocumentation(path="/all")
    public List<EmployeeAllDto> getEmployeeList() {
        //http://localhost:8080/employee/all
        return employeeService.getEmployeeList();
    }

    @GetDriverListDocumentation(path = "/drivers")
    public List<EmployeeDto> getDriverList() {
        //http://localhost:8080/employee/drivers
        return employeeService.getDriverList();
    }

    @CreateEmployeeDocumentation(path = "/registration")
    public EmployeeAfterRegistrationDto createEmployee(@RequestBody EmployeeRegistrationDto employeeRegistrationDto) {
        //http://localhost:8080/employee/registration
        return employeeService.createEmployee(employeeRegistrationDto);
    }

    @GetEmployeeWithOneOrMoreVehicleMaintenanceDocumentation(path ="/with-vehicle-maintenance")
    public List<EmployeeWithVehicleAndMaintenanceDto> getEmployeeWithOneOrMoreVehicleMaintenance() {
        //http://localhost:8080/employee/with-vehicle-maintenance
        return employeeService.getEmployeeWithOneOrMoreVehicleMaintenance();
    }
}
