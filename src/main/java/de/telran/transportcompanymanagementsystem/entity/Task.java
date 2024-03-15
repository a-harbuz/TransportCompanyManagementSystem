package de.telran.transportcompanymanagementsystem.entity;

import de.telran.transportcompanymanagementsystem.entity.enums.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID taskId;

    @Column(name = "transportation_date")
    private LocalDateTime transportationDate;

    @Column(name = "address_from")
    private String addressFrom;

    @Column(name = "address_to")
    private String addressTo;

    @Column(name = "weight_cargo")
    private float weightCargo;

    @Column(name = "waybill_number")
    private String waybillNumber;

    @Column(name = "waybill_cost")
    private BigDecimal waybillCost;

    @Column(name = "distance_traveled_kilometers")
    private int distanceTraveledKilometers;

    @Column(name = "fuel_costs_traveled")
    private BigDecimal fuelCostsTraveled;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "comment_if_task_canceled")
    private String commentIfTaskCanceled;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //Relationships
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

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
