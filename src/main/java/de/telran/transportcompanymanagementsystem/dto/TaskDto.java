package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.telran.transportcompanymanagementsystem.entity.enums.TaskStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class TaskDto {
    private UUID taskId;
    private LocalDateTime transportationDate;
    private String addressFrom;
    private String addressTo;
    private Float weightCargo;
    private String waybillNumber;
    private BigDecimal waybillCost;
    private Integer distanceTraveledKilometers;
    private BigDecimal fuelCostsTraveled;
    private TaskStatus taskStatus;
    private String commentIfTaskCanceled;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
    private ContractDtoShort contract;
    private CompanyDto company;
    private VehicleDto vehicle;
    private EmployeeDto employee;
}
