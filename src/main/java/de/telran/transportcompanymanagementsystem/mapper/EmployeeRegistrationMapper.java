package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.EmployeeAfterRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeRegistrationDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import org.mapstruct.*;

import java.sql.Timestamp;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Timestamp.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeRegistrationMapper {
    //Заполняем и основные и связанные поля
    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "driver", ignore = true),
            @Mapping(target = "working", ignore = true),
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),

            @Mapping(target = "employeeInfo.address", source = "address"),
            @Mapping(target = "employeeInfo.phone", source = "phone"),
            @Mapping(target = "employeeInfo.drivingLicenseCategory", source = "drivingLicenseCategory"),
            @Mapping(target = "employeeInfo.login", expression = "java(generateLogin())"),
            @Mapping(target = "employeeInfo.password", expression = "java(generatePassword())"),
            @Mapping(target = "employeeInfo.createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))")
    })
    Employee toEntity(EmployeeRegistrationDto employeeRegistrationDto);
    EmployeeAfterRegistrationDto toDto (Employee employee);

//    @AfterMapping
//    default void GenerateLoginAndPassword(@MappingTarget User)
    default String generateLogin(){
        return "1";
    }

    default String generatePassword(){
        return "2";
    }
}
