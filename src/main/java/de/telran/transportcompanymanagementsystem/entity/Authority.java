package de.telran.transportcompanymanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "authority")
@NoArgsConstructor
@Getter
@Setter
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID aId;

    @Column(name = "authority_name")
    private String authorityName;

    //Relationships
    @ManyToMany(mappedBy = "authorities")
    @JsonIgnore
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Authority authority = (Authority) o;
        return Objects.equals(aId, authority.aId) && Objects.equals(authorityName, authority.authorityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(aId, authorityName);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "aId=" + aId +
                ", authorityName='" + authorityName + '\'' +
                '}';
    }
}