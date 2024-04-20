package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import de.telran.transportcompanymanagementsystem.entity.Task;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Timestamp.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    List<EmployeeWithVehicleAndMaintenanceDto> toDtoEmployeeWithVehicleAndMaintenance (List<Employee> employees);
    @AfterMapping
    default void getMaintenance(@MappingTarget EmployeeWithVehicleAndMaintenanceDto employeeWithVehicleAndMaintenanceDto,
                                Employee employee) {
        List<List<Maintenance>> maintenances = employee.getTasks().stream()
                .map(Task::getVehicle)
                .map(Vehicle::getMaintenances).toList();

        employeeWithVehicleAndMaintenanceDto.setMaintenanceList(maintenances);
    }

    List<EmployeeDto> toDtoList (List<Employee> employees);
    EmployeeDto toDto (Employee employee);
}
