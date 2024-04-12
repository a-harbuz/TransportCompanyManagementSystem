package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.telran.transportcompanymanagementsystem.entity.enums.ServiceType;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;


@Data
@AllArgsConstructor
public class VehicleWithMaintenanceDto {
    /*
      Vehicle.class
     */
    private UUID vehicleId;
    private VehicleType vehicleType;
    private String name;
    private String model;
    private String yearManufacture;
    private String carNumber;
    private int initialKilometers;
    /*
      Maintenance.class
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp maintenanceDate;
    private ServiceType serviceType;
    private BigDecimal maintenanceCost;
    private int maintenanceKilometers;
    /*
      Company.class
     */
    private String companyName;
}
