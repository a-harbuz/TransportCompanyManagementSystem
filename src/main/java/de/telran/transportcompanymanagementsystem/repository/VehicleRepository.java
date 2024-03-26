package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Vehicle findByCarNumber (String string);
    @NonNull
    Vehicle saveAndFlush (@NonNull Vehicle vehicle);
    void deleteVehicleByVehicleId (UUID uuid);
}
