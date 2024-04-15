package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import de.telran.transportcompanymanagementsystem.entity.enums.TaskStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TaskDto {
    private UUID taskId;
    private LocalDateTime transportationDate;
    private String addressFrom;
    private String addressTo;
    private Float weightCargo;
    private String waybillNumber;
    private BigDecimal waybillCost; //BigDecimal
    private Integer distanceTraveledKilometers;
    private BigDecimal fuelCostsTraveled; //BigDecimal
    private TaskStatus taskStatus;
    private String commentIfTaskCanceled;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
    private String contract_id;
    private String company_id;
    private String vehicle_id;
    private String employee_id;
}
