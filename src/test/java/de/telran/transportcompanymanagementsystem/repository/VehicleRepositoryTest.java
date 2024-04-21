package de.telran.transportcompanymanagementsystem.repository;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import util.CheckUuidPattern;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class VehicleRepositoryTest {

    private static final String TEST_ID = "26e41ad9-0482-4808-9dbb-c917631f1b56";
    private static final String TEST_CAR_NUMBER = "AE2387KM";

    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void testGet() {
        Optional<Vehicle> vehicle = vehicleRepository.findById(UUID.fromString(TEST_ID));
        Assertions.assertTrue(vehicle.isPresent());
        Assertions.assertEquals(TEST_ID, vehicle.get().getVehicleId().toString());
    }
    @Test
    void findByCarNumber() {
        Vehicle vehicle = vehicleRepository.findByCarNumber(TEST_CAR_NUMBER);
        assertNotNull(vehicle);
        assertEquals(TEST_CAR_NUMBER, vehicle.getCarNumber());
    }

    @Test
    void getVehicleWithMaintenanceCostMoreOrEqual() {
        float maintenanceCost = 500f;
        List<Vehicle> vehicleList = vehicleRepository.
                getVehicleWithMaintenanceCostMoreOrEqual(BigDecimal.valueOf(maintenanceCost));
        assertFalse(vehicleList.isEmpty());
        long count = vehicleList.stream()
                .map(Vehicle::getMaintenances)
                .flatMap(List::stream)
                .filter(maintenance -> maintenance.getMaintenanceCost().compareTo(BigDecimal.valueOf(maintenanceCost)) > 0)
                .count();
        assertEquals(count, 2);
    }

    @Test
    void testInsert() {
        Vehicle newVehicle = new Vehicle();
        Vehicle returnVehicle = vehicleRepository.save(newVehicle);
        assertNotNull(returnVehicle);
        assertTrue(
                returnVehicle.getVehicleId().toString().matches(CheckUuidPattern.getUuidPattern()));
        Optional<Vehicle> findVehicle = vehicleRepository.findById(returnVehicle.getVehicleId());
        Assertions.assertTrue(findVehicle.isPresent());
        Assertions.assertEquals(returnVehicle.getVehicleId(), findVehicle.get().getVehicleId());
    }

    @Test
    void testEdit() {
        Optional<Vehicle> vehicle = vehicleRepository.findById(UUID.fromString(TEST_ID));
        assertTrue(vehicle.isPresent());

        Vehicle getVehicle = vehicle.get();
        assertEquals(TEST_ID, getVehicle.getVehicleId().toString());

        getVehicle.setName("CAR_TEST_NAME");
        Vehicle returnVehicleAfterSave = vehicleRepository.save(getVehicle);
        assertNotNull(returnVehicleAfterSave);
        assertEquals(TEST_ID, returnVehicleAfterSave.getVehicleId().toString());

        Optional<Vehicle> findVehicle = vehicleRepository.findById(UUID.fromString(TEST_ID));
        assertTrue(findVehicle.isPresent());
        assertEquals("CAR_TEST_NAME", findVehicle.get().getName());
    }

    @Test
    void testDelete() {
        Vehicle newVehicle = new Vehicle();
        Vehicle returnVehicle = vehicleRepository.save(newVehicle);
        assertNotNull(returnVehicle);
        assertTrue(
                returnVehicle.getVehicleId().toString().matches(CheckUuidPattern.getUuidPattern()));
        vehicleRepository.delete(returnVehicle);

        Optional<Vehicle> findVehicleAfterDelete = vehicleRepository.findById(returnVehicle.getVehicleId());
        assertFalse(findVehicleAfterDelete.isPresent());
    }
}