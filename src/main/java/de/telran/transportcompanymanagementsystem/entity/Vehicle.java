package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.util.UUID;
import java.sql.Timestamp;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vehicle {
    private UUID vehicleId;
    private VehicleType vehicleType;
    private String name;
    private String model;
    private String yearManufacture;
    private String carNumber;
    private int initialKilometers;
    private float price;
    private VehicleStatus vehicleStatus;
    private Timestamp createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(vehicleId, vehicle.vehicleId) && Objects.equals(name, vehicle.name) && Objects.equals(model, vehicle.model) && Objects.equals(yearManufacture, vehicle.yearManufacture) && Objects.equals(carNumber, vehicle.carNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, name, model, yearManufacture, carNumber);
    }
}
