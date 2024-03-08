package de.telran.transportcompanymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contact_first_name")
    private String contactFirstName;

    @Column(name = "contact_last_name")
    private String contactLastName;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "created_at")
    private Timestamp createdAt;

    //Relationships
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Contract> contracts;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Task> task;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(companyId, company.companyId) && Objects.equals(companyName, company.companyName) && Objects.equals(address, company.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, companyName, address);
    }
}
