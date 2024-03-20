package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeeName();
    String getEmployeeById(String id);
}
