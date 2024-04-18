package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.exception.DataNotValidException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Timestamp.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeMapper {
//    @Mappings({
//            @Mapping(target = "stCount", source = "st_count") //
//    })
    List<EmployeeWithVehicleAndMaintenanceDto> toDtoEmployeeWithVehicleAndMaintenance (List<Employee> employees);
//    @AfterMapping
//    default void CheckFirstNameLastNameAndLogin(@MappingTarget EmployeeWithVehicleAndMaintenanceDto employeeWithVehicleAndMaintenanceDto,
//            Employee employees) {
//        System.out.println(">>>>>>>");
//        //System.out.println(employees.getStCount());
//    }
//    default EmployeeWithVehicleAndMaintenanceDto map(Employee employee) {
//        //EmployeeWithVehicleAndMaintenanceDto employeeInfoDTO = new EmployeeWithVehicleAndMaintenanceDto();
//        //employeeInfoDTO.setStCount(employee.getStCount());
//
//        return employeeInfoDTO;
//    }


    List<EmployeeDto> toDto (List<Employee> employees);
}
