package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import jakarta.annotation.Nonnull;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Vehicle findByCarNumber (String string);
    Vehicle saveAndFlush (Vehicle vehicle);
}