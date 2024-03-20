package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.exception.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Override
    public List<Employee> getEmployeeName() {
        return null;
    }

    @Override
    public String getNameEmployeeById(String id) {
        return (employeeRepository.findById(UUID.fromString(id)).isPresent()) ?
                employeeRepository.findById(UUID.fromString(id)).get().getFirstName() //"Есть такой Работник"
                : ErrorMessage.EMPLOYEE_NOT_FOUND; // "Нету такого";
    }

    @Override
    public Employee getEmployeeById(String id) {
//        return (employeeRepository.findById(UUID.fromString(id)).isPresent()) ?
//                employeeRepository.findById(UUID.fromString(id)).get()
//                : ErrorMessage.EMPLOYEE_NOT_FOUND;
        try {
            return employeeRepository.findById(UUID.fromString(id)).orElseThrow(()-> new UserPrincipalNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND));
        } catch (UserPrincipalNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
