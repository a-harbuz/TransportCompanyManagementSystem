package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Driver {
    private UUID driverId;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private DrivingLicenseCategory drivingLicenseCategory;
    boolean isWorking;
    private Timestamp createdAt;
}
