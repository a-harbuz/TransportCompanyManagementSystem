package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Employee;

import java.util.List;

public interface EmployeeService {

    String getNameEmployeeById(String id);
    Employee getEmployeeById(String id);
    List<Employee> getEmployeeList();
    List<Employee> getDriverList();

}
