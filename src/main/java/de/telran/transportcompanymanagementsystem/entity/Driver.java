package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Driver driver = (Driver) o;
        return Objects.equals(driverId, driver.driverId) && Objects.equals(firstName, driver.firstName) && Objects.equals(lastName, driver.lastName) && Objects.equals(address, driver.address) && Objects.equals(phone, driver.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driverId, firstName, lastName, address, phone);
    }
}
