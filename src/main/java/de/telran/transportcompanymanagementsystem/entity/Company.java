package de.telran.transportcompanymanagementsystem.entity;

import lombok.*;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Company {
    private UUID companyId;
    private String companyName;
    private String contactFirstName;
    private String contactLastName;
    private String email;
    private String address;
    private String phone;
    private Timestamp createdAt;

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
