package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.*;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import de.telran.transportcompanymanagementsystem.entity.Role;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.EmployeeExistException;
import de.telran.transportcompanymanagementsystem.exception.EmployeeNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.RoleNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.EmployeeMapper;
import de.telran.transportcompanymanagementsystem.mapper.EmployeeRegistrationMapper;
import de.telran.transportcompanymanagementsystem.repository.EmployeeInfoRepository;
import de.telran.transportcompanymanagementsystem.repository.EmployeeRepository;
import de.telran.transportcompanymanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeInfoRepository employeeInfoRepository;
    private final EmployeeRegistrationMapper employeeRegistrationMapper;
    private final EmployeeMapper employeeMapper;
    private final RoleRepository roleRepository;


    @Override
    public EmployeeDto getEmployeeById(String id) {
          return employeeMapper.toDto(employeeRepository.findById(UUID.fromString(id))
                  .orElseThrow(()-> new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND)));
    }

    @Override
    public List<EmployeeAllDto> getEmployeeList() {
        return employeeMapper.toAllDtoList(employeeRepository.findAll());
    }

    @Override
    public List<EmployeeDto> getDriverList() {
        return employeeMapper.toDtoList(employeeRepository.findAll()
                .stream()
                .filter(Employee::isDriver)
                .toList());
    }

    @Override
    @Transactional
    public EmployeeAfterRegistrationDto createEmployee(EmployeeRegistrationDto employeeRegistrationDto) {
        Employee employee = employeeRepository.findByFirstNameAndLastName(
                employeeRegistrationDto.getFirstName(), employeeRegistrationDto.getLastName());
        if (employee != null) {
            throw new EmployeeExistException(ErrorMessage.EMPLOYEE_EXIST);
        }
        EmployeeInfo employeeInfo = employeeInfoRepository.findByLogin(employeeRegistrationDto.getLogin());
        if (employeeInfo != null) {
            throw new EmployeeExistException(ErrorMessage.EMPLOYEE_WITH_LOGIN_EXIST);
        }

        Role dafaultRole = roleRepository.findByRoleName("ROLE_USER");
        if (dafaultRole==null) {
            throw new RoleNotFoundException(ErrorMessage.DEFAULT_ROLE_NOT_FOUND);
        }
        Set<Role> setRole = new HashSet<>();
        setRole.add(dafaultRole);
        Employee newEmployee = employeeRegistrationMapper.toEntity(employeeRegistrationDto, setRole);

        String originalPassword = newEmployee.getEmployeeInfo().getPassword();
        String hashedPassword = BCrypt.hashpw(originalPassword, BCrypt.gensalt());
        newEmployee.getEmployeeInfo().setPassword(hashedPassword);

        Employee employeeAfterSaving = employeeRepository.saveAndFlush(newEmployee);

        String hashedPasswordAfterSaving = employeeAfterSaving.getEmployeeInfo().getPassword();
        //check password
        if (!BCrypt.checkpw(originalPassword, hashedPasswordAfterSaving)) {
            throw new DataNotFoundException(ErrorMessage.ERROR_SAVING_PASSWORD);
        }

        employeeAfterSaving.getEmployeeInfo().setPassword(originalPassword);
        return employeeRegistrationMapper.toDto(employeeAfterSaving);
    }

    @Override
    public List<EmployeeWithVehicleAndMaintenanceDto> getEmployeeWithOneOrMoreVehicleMaintenance() {
        List<Employee> employees = employeeRepository.findEmployeeWithOneOrMoreVehicleMaintenance();
        employees.forEach(System.out::println);
        return employeeMapper.toDtoEmployeeWithVehicleAndMaintenance(employees);
    }
}
