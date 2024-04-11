package de.telran.transportcompanymanagementsystem.dto;

import lombok.Data;

@Data
public class CompanyDto {
    private String companyName;
    private String contactFirstName;
    private String contactLastName;
    private String address;
    private String email;
    private String phone;
}
