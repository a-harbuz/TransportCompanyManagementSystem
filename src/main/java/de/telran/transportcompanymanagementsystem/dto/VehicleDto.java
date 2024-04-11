package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleStatus;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class VehicleDto {
    private String vehicleId;
    private VehicleType vehicleType;
    private String name;
    private String model;
    private String yearManufacture;
    private String carNumber;
    private int initialKilometers;
    private float price;
    private VehicleStatus vehicleStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
}
