package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/name/{id}")
    public String getNameEmployeeById(@PathVariable("id") String id) {
        //http://localhost:8080/employee/name/1c9859bd-8d9b-49e0-88d7-58f8f3c1c4b3
        return employeeService.getNameEmployeeById(id);
    }
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") String id) {
        //http://localhost:8080/employee/a060e1b8-7c07-4619-85dc-32c6a5afa269
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/all")
    public List<Employee> getEmployeeList() {
        //http://localhost:8080/employee/all
        return employeeService.getEmployeeList();
    }

    @GetMapping("/drivers")
    public List<Employee> getDriversList() {
        //http://localhost:8080/employee/drivers
        return employeeService.getDriverList();
    }

}
