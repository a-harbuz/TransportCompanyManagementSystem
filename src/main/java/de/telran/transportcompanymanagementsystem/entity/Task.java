package de.telran.transportcompanymanagementsystem.entity;
import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {
    private UUID taskId;
    private LocalDateTime transportationDate;
    private String addressFrom;
    private String addressTo;
    private float weightCargo;
    private String waybillNumber;
    private BigDecimal waybillCost;
    private int distanceTraveledKilometers;
    private BigDecimal fuelCostsTraveled;
    private TaskStatus taskStatus;
    private String commentIfTaskCanceled;
    private Timestamp createdAt;
    private Contract contractId;
    private Company companyId;
    private Vehicle vehicleId;
    private Driver driverId;
}
