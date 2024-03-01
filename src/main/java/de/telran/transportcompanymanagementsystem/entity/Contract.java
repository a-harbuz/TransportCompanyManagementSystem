package de.telran.transportcompanymanagementsystem.entity;
import de.telran.transportcompanymanagementsystem.entity.enums.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return Objects.equals(contractId, contract.contractId) && Objects.equals(contractNumber, contract.contractNumber) && Objects.equals(contractName, contract.contractName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractId, contractNumber, contractName);
    }
}
