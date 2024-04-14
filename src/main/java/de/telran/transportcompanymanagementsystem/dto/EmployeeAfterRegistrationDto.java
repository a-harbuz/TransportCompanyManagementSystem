package de.telran.transportcompanymanagementsystem.dto;

import lombok.Value;

@Value
public class EmployeeAfterRegistrationDto {
    String operation = "EMPLOYEE CREATION";
    String status = "CREATED";
    String creationDate;
    String login;
    String password;
}
