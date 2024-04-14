package de.telran.transportcompanymanagementsystem.dto;

import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
import lombok.Data;

@Data
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
