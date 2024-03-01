package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vehicle {
    private UUID vehicleId;
    private String accountNumber;
    private VehicleType vehicleType;
    private String name;
    private String model;
    private String yearManufacture;
    private String сarNumber;
    private int initialKilometers;
    private float price;
    private VehicleStatus vehicleStatus;
    private Timestamp createdAt;
}
