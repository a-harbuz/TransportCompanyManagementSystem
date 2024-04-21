package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleStatus;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class VehicleDto {
    private UUID vehicleId;
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
