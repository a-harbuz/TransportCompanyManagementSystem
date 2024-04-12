package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.telran.transportcompanymanagementsystem.entity.enums.ServiceType;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class MaintenanceDto {
    private UUID mId;
    private ServiceType serviceType;
    private BigDecimal maintenanceCost;
    private int maintenanceKilometers;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
    private VehicleDto vehicle;
    private CompanyDto company;

}
