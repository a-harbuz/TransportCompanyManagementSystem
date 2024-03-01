package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
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
}
