package de.telran.transportcompanymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "employee_info")
@Data
public class EmployeeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID employeeInfoId;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "driving_license_category")
    @Enumerated(EnumType.STRING)
    private DrivingLicenseCategory drivingLicenseCategory;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //Relationships
    @OneToOne(mappedBy = "employeeInfo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Employee employee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "employeeInfo_role",
            joinColumns = @JoinColumn(name = "employeeInfo_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeInfo that = (EmployeeInfo) o;
        return Objects.equals(employeeInfoId, that.employeeInfoId) && Objects.equals(address, that.address) && Objects.equals(phone, that.phone) && drivingLicenseCategory == that.drivingLicenseCategory && Objects.equals(login, that.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeInfoId, address, phone, drivingLicenseCategory, login);
    }

    @Override
    public String toString() {
        return "EmployeeInfo{" +
                "employeeInfoId=" + employeeInfoId +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", drivingLicenseCategory=" + drivingLicenseCategory +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
