package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.exception.VehicleNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
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
            vehicle.setCarNumber(newCarNumber);
            return vehicleRepository.saveAndFlush(vehicle);
        } else {
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
    public Vehicle create(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
