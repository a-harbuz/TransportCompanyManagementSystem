package de.telran.transportcompanymanagementsystem.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class ContractDtoShort {
    private UUID contractId;
    private String contractNumber;
    private String contractName;
    private BigDecimal costTransportationUnderContract;
    private BigDecimal totalCostTransportedGoods;
    private String contractStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp createdAt;
}
