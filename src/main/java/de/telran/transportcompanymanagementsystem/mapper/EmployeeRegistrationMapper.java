package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.EmployeeAfterRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeRegistrationDto;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import de.telran.transportcompanymanagementsystem.exception.DataNotValidException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import org.mapstruct.*;
import com.github.javafaker.Faker;
import java.sql.Timestamp;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Timestamp.class,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeRegistrationMapper {
    //Fill in both the main and related fields
    @Mappings({
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "driver", source = "driver"),
            @Mapping(target = "working", expression = "java(true)"), //ignore = true
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),

            @Mapping(target = "employeeInfo.address", source = "address"),
            @Mapping(target = "employeeInfo.phone", source = "phone"),
            @Mapping(target = "employeeInfo.drivingLicenseCategory", source = "drivingLicenseCategory"),
            @Mapping(target = "employeeInfo.login", source = "login"),
            @Mapping(target = "employeeInfo.password", expression = "java(generatePassword())"),
            @Mapping(target = "employeeInfo.createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))")
    })
    Employee toEntity(EmployeeRegistrationDto employeeRegistrationDto);

    @AfterMapping
    default void CheckFirstNameLastNameAndLogin(@MappingTarget Employee employee) {
        if (employee.getFirstName().length()<3 || employee.getLastName().length()<3) {
            throw new DataNotValidException(ErrorMessage.EMPLOYEE_NOT_VALID_NAME);
        }
        if (employee.getEmployeeInfo().getLogin().length()<5) {
            throw new DataNotValidException(ErrorMessage.EMPLOYEE_NOT_VALID_LOGIN);
        }
    }

    @Mappings({
            @Mapping(target = "login", source = "employeeInfo.login"),
            @Mapping(target = "password", source = "employeeInfo.password"),
            @Mapping(target = "creationDate", source = "employeeInfo.createdAt")
    })
    EmployeeAfterRegistrationDto toDto (Employee employee);

    default String generatePassword() {
        Faker faker = new Faker();
        return faker.internet().password(8, 12);
    }
}
