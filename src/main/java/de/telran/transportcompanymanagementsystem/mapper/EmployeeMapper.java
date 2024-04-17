package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Timestamp.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
    List<EmployeeWithVehicleAndMaintenanceDto> toDtoEmployeeWithVehicleAndMaintenance (List<Employee> employees);
    List<EmployeeDto> toDto (List<Employee> employees);
}
