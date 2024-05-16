package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.telran.transportcompanymanagementsystem.entity.Role;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Data
public class EmployeeAllDto {
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private boolean isDriver;
    private boolean isWorking;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
    private String login;
    private List<Role> roles;
}
