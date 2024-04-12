package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(String id);
    List<Employee> getEmployeeList();
    List<Employee> getDriverList();

}
