package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
    Vehicle findByCarNumber (String string);
    @Query("select v from Vehicle v join v.maintenances m where m.maintenanceCost >= :maintenanceCost")
    List<Vehicle> getVehicleWithMaintenanceCostMoreOrEqual (@Param("maintenanceCost") BigDecimal maintenanceCost);
}
