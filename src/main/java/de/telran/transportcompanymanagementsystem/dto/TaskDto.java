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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
}
