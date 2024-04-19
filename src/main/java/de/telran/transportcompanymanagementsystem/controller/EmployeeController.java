package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.dto.EmployeeAfterRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
    public EmployeeDto getEmployeeById(@PathVariable("id") String id) {
        //http://localhost:8080/employee/a060e1b8-7c07-4619-85dc-32c6a5afa269
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/all")
    public List<EmployeeDto> getEmployeeList() {
        //http://localhost:8080/employee/all
        return employeeService.getEmployeeList();
    }

    @GetMapping("/drivers")
    @Operation(
            summary = "Looking for employees who are drivers..",
            description = "Looking for employees who are drivers..")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned employees",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = Employee.class)))
            })
    public List<EmployeeDto> getDriverList() {
        //http://localhost:8080/employee/drivers
        return employeeService.getDriverList();
    }

    @PostMapping("/registration")
    public EmployeeAfterRegistrationDto createEmployee(@RequestBody EmployeeRegistrationDto employeeRegistrationDto) {
        //http://localhost:8080/employee/registration
        return employeeService.createEmployee(employeeRegistrationDto);
    }

    @GetMapping("/with-vehicle-maintenance")
    @Operation(
            summary = "Looks for employees who contain tasks in which vehicles contain one or more maintenance jobs.",
            description = "Looks for employees who contain tasks in which vehicles contain one or more maintenance jobs.")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned employees",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(
                                    schema = @Schema(implementation = EmployeeWithVehicleAndMaintenanceDto.class)))
            })
    public List<EmployeeWithVehicleAndMaintenanceDto> getEmployeeWithOneOrMoreVehicleMaintenance() {
        //http://localhost:8080/employee/with-vehicle-maintenance
        return employeeService.getEmployeeWithOneOrMoreVehicleMaintenance();
    }
}
