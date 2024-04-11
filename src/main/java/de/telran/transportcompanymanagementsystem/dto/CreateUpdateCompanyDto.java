package de.telran.transportcompanymanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.UUID;

@Data
@Schema(description = "Data for creation company")
public class CreateUpdateCompanyDto {
    private UUID companyId;
    private String companyName;
    private String contactFirstName;
    private String contactLastName;
    private String email;
    private String address;
    private String phone;
}
