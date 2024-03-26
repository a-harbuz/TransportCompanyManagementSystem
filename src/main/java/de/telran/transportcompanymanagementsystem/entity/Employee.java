package de.telran.transportcompanymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID employeeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "is_driver")
    private boolean isDriver;

    @Column(name = "is_working")
    private boolean isWorking;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //Relationships
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Task> tasks;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_info_id", referencedColumnName = "id")
    @JsonIgnore
    private EmployeeInfo employeeInfo;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return isDriver == employee.isDriver && isWorking == employee.isWorking && Objects.equals(employeeId, employee.employeeId) && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, firstName, lastName, isDriver, isWorking);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isDriver=" + isDriver +
                ", isWorking=" + isWorking +
                ", createdAt=" + createdAt +
                '}';
    }
}
