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
    public List<Employee> getDriverList() {
        //http://localhost:8080/employee/drivers
        return employeeService.getDriverList();
    }

}
