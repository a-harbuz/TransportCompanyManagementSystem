package de.telran.transportcompanymanagementsystem.dto;

import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeRegistrationDto {
    /*
    Employee.class
    */
    private String firstName;
    private String lastName;
    private boolean isDriver;
    /*
    EmployeeInfo.class
    */
    private String login;
    private String address;
    private String phone;
    private DrivingLicenseCategory drivingLicenseCategory;
}
