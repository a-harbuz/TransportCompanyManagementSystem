package de.telran.transportcompanymanagementsystem.entity;
import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return Objects.equals(taskId, task.taskId) && Objects.equals(transportationDate, task.transportationDate) && Objects.equals(addressFrom, task.addressFrom) && Objects.equals(addressTo, task.addressTo) && Objects.equals(waybillNumber, task.waybillNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, transportationDate, addressFrom, addressTo, waybillNumber);
    }
}
