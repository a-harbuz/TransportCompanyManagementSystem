package de.telran.transportcompanymanagementsystem.dto;

import de.telran.transportcompanymanagementsystem.entity.enums.VehicleStatus;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Schema(description = "Data for creation vehicle")
public class CreateVehicleDto {
    @NotEmpty
    private VehicleType vehicleType;
    @NotEmpty
    private String name;
    private String model;
    private String yearManufacture;
    @NotEmpty
    private String carNumber;
    private int initialKilometers;
    private float price;
    @NotEmpty
    private VehicleStatus vehicleStatus;
}
