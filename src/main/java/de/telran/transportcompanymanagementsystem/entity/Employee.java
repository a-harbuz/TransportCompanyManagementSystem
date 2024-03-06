package de.telran.transportcompanymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @Column(name = "id")
    private UUID employeeId;

    @Column(name = "is_driver")
    private boolean isDriver;

    @Column(name = "is_working")
    private boolean isWorking;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "employee_info_id")
    private UUID employeeInfoId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return isDriver == employee.isDriver && isWorking == employee.isWorking && Objects.equals(employeeId, employee.employeeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, isDriver, isWorking);
    }
}
