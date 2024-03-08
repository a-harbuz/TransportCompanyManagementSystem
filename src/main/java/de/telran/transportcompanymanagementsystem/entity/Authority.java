package de.telran.transportcompanymanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authorities")
@NoArgsConstructor
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "a_id")
    private UUID aId;

    @Column(name = "authority_name")
    private String authorityName;

    //Relationships
    @ManyToMany(mappedBy = "authorities")
    private Set<Role> roles;
}