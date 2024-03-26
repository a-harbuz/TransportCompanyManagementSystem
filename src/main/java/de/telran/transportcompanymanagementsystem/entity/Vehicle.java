package de.telran.transportcompanymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telran.transportcompanymanagementsystem.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "vehicle")
@Data
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    //Relationships
    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    //@JsonBackReference
    @JsonIgnore
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
    //@JsonBackReference
    private List<Task> tasks;

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

    @Override
    public String toString() {
        return "Vehicle{" +
                "vehicleId=" + vehicleId +
                ", vehicleType=" + vehicleType +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", yearManufacture='" + yearManufacture + '\'' +
                ", carNumber='" + carNumber + '\'' +
                ", initialKilometers=" + initialKilometers +
                ", price=" + price +
                ", vehicleStatus=" + vehicleStatus +
                ", createdAt=" + createdAt +
                '}';
    }
}
