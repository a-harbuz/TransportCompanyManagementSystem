package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Vehicle {

    @Id
    @Column(name = "id")
    private UUID vehicleId;

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;

    @Column(name = "name")
    private String name;

    @Column(name = "model")
    private String model;

    @Column(name = "year_manufacture")
    private String yearManufacture;

    @Column(name = "car_number")
    private String carNumber;

    @Column(name = "initial_kilometers")
    private int initialKilometers;

    @Column(name = "price")
    private float price;

    @Column(name = "vehicle_status")
    @Enumerated(EnumType.STRING)
    private VehicleStatus vehicleStatus;

    @Column(name = "created_at")
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
