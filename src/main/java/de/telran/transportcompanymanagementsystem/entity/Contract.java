package de.telran.transportcompanymanagementsystem.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import de.telran.transportcompanymanagementsystem.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "contract")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID contractId;

    @Column(name = "contract_number")
    private String contractNumber;

    @Column(name = "contract_name")
    private String contractName;

    @Column(name = "cost_transportation_under_contract")
    private BigDecimal costTransportationUnderContract;

    @Column(name = "total_cost_transported_goods")
    private BigDecimal totalCostTransportedGoods;

    @Column(name = "contract_status")
    @Enumerated(EnumType.STRING)
    private ContractStatus contractStatus;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //Relationships
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    @JsonBackReference
    private Company company;

    @OneToMany(mappedBy = "contract", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Task> tasks;

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

    @Override
    public String toString() {
        return "Contract{" +
                "contractId=" + contractId +
                ", contractNumber='" + contractNumber + '\'' +
                ", contractName='" + contractName + '\'' +
                ", costTransportationUnderContract=" + costTransportationUnderContract +
                ", totalCostTransportedGoods=" + totalCostTransportedGoods +
                ", contractStatus=" + contractStatus +
                ", createdAt=" + createdAt +
                ", company=" + company +
                '}';
    }
}
