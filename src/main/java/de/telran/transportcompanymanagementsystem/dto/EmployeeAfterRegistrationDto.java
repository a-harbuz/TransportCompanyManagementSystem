package de.telran.transportcompanymanagementsystem.dto;

import lombok.Value;

@Value
public class EmployeeAfterRegistrationDto {
    String operation;
    String status;
    String login;
    String password;
}
