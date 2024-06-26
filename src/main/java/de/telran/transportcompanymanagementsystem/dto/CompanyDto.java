package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CompanyDto {
    private UUID companyId;
    private String companyName;
    private String contactFirstName;
    private String contactLastName;
    private String address;
    private String email;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
}
