package de.telran.transportcompanymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@Getter
@Setter
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "r_id")
    private UUID rId;

    @Column(name = "role_name")
    private String roleName;

    //Relationships
    @ManyToMany(mappedBy = "roles")
    private Set<EmployeeInfo> employeeInfos;

    @ManyToMany(fetch = FetchType.LAZY)
    //role_authority - The join table
    @JoinTable(name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Set<Authority> authorities;
}
