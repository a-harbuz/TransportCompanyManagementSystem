package de.telran.transportcompanymanagementsystem.entity;
import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Contract {
    UUID contractId;
    String contractNumber;
    String contractName;
    BigDecimal costTransportationUnderContract;
    BigDecimal totalCostTransportedGoods;
    ContractStatus contractStatus;
    Timestamp createdAt;
    Company companyId;

}
