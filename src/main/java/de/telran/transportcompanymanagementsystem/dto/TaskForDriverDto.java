package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskForDriverDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime transportationDate;
    private String addressFrom;
    private String addressTo;
    private float weightCargo;
    private String waybillNumber;
}
