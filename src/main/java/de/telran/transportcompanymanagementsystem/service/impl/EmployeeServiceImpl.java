package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.exception.EmployeeNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public String getNameEmployeeById(String id) {
        return (employeeRepository.findById(UUID.fromString(id)).isPresent()) ?
                //There is such Employee
                employeeRepository.findById(UUID.fromString(id)).get().getFirstName()
                //There is not such Employee
                : ErrorMessage.EMPLOYEE_NOT_FOUND;
    }

    @Override
    public Employee getEmployeeById(String id) {
          return employeeRepository.findById(UUID.fromString(id))
                  .orElseThrow(()-> new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public List<Employee> getEmployeeList() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> getDriverList() {
        return employeeRepository.findAll()
                .stream()
                .filter(Employee::isDriver)
                .toList();
    }
}
