package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.EmployeeAfterRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import de.telran.transportcompanymanagementsystem.entity.Role;
import de.telran.transportcompanymanagementsystem.exception.EmployeeExistException;
import de.telran.transportcompanymanagementsystem.exception.EmployeeNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.RoleNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.EmployeeMapper;
import de.telran.transportcompanymanagementsystem.mapper.EmployeeRegistrationMapper;
import de.telran.transportcompanymanagementsystem.repository.EmployeeInfoRepository;
import de.telran.transportcompanymanagementsystem.repository.EmployeeRepository;
import de.telran.transportcompanymanagementsystem.repository.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

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
    public List<EmployeeDto> getEmployeeList() {
        return employeeMapper.toDtoList(employeeRepository.findAll());
    }

    @Override
    public List<EmployeeDto> getDriverList() {
        return employeeMapper.toDtoList(employeeRepository.findAll()
                .stream()
                .filter(Employee::isDriver)
                .toList());
    }

    @Override
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

        Role dafaultRole = roleRepository.findByRoleName("User");
        if (dafaultRole==null) {
            throw new RoleNotFoundException(ErrorMessage.DEFAULT_ROLE_NOT_FOUND);
        }
        Set<Role> setRole = new HashSet<>();
        setRole.add(dafaultRole);
        Employee newEmployee = employeeRegistrationMapper.toEntity(employeeRegistrationDto, setRole);

        Employee employeeAfterSaving = employeeRepository.saveAndFlush(newEmployee);
        return employeeRegistrationMapper.toDto(employeeAfterSaving);
    }

    @Override
    @Transactional
    public List<EmployeeWithVehicleAndMaintenanceDto> getEmployeeWithOneOrMoreVehicleMaintenance() {
        List<Employee> employees = employeeRepository.findEmployeeWithOneOrMoreVehicleMaintenance();
        employees.forEach(System.out::println);
        return employeeMapper.toDtoEmployeeWithVehicleAndMaintenance(employees);
    }
}
