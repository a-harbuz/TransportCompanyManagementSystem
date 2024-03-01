package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Maintenance {
    private UUID mId;
    private ServiceType serviceType;
    private BigDecimal maintenanceCost	;
    int maintenanceKilometers;
    Timestamp createdAt;
    Vehicle vehicleId;
    Company maintenanceCompany;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Maintenance that = (Maintenance) o;
        return maintenanceKilometers == that.maintenanceKilometers && Objects.equals(mId, that.mId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mId, maintenanceKilometers);
    }
}
