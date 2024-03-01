package de.telran.transportcompanymanagementsystem.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    private UUID companyId;
    private String companyName;
    private String contactFirstName;
    private String contactLastName;
    private String email;
    private String address;
    private String phone;
    private Timestamp createdAt;

}
