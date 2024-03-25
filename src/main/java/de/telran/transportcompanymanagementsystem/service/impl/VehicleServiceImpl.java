package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.exception.VehicleNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errorMessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.VehicleRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle getVehicleById(String id) {
        return vehicleRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND));
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    @Override
    public Vehicle getVehicleByCarNumber(String carNumber) {
        Vehicle vehicle = vehicleRepository.findByCarNumber(carNumber);
        if (vehicle != null) {
            return vehicle;
        } else {
            throw new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND);
        }
    }

    @Override
    public Vehicle setVehicleByCarNumber(String carNumber, String newCarNumber) {
        Vehicle vehicle = vehicleRepository.findByCarNumber(carNumber);
        if (vehicle != null) {
            // Update your car number to a new one (newCarNumber)
            vehicle.setCarNumber(newCarNumber);
            // Save the updated Vehicle object to the database
            vehicleRepository.saveAndFlush(vehicle);
            return vehicle;
        } else {
            // If the car with the specified number is not found, return null or throw an exception
            throw new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND);
        }
    }

    @Override
    public void deleteVehicleByCarNumber(String carNumber) {
        Vehicle vehicle = vehicleRepository.findByCarNumber(carNumber);
        if (vehicle != null) {
            vehicleRepository.deleteVehicleByVehicleId(vehicle.getVehicleId());
        } else {
            throw new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND);
        }
    }

    @Override
    public void deleteVehicleById(String id) {
        vehicleRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public void saveOrUpdateVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }
}
