package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.CreateVehicleDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleDto;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.exception.VehicleNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.VehicleMapper;
import de.telran.transportcompanymanagementsystem.repository.VehicleRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper mapper;

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
            throw new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND_BY_CAR_NUMBER);
        }
    }

    @Override
    public void deleteVehicleById(String id) {
        vehicleRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND));
        vehicleRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<Vehicle> getVehicleWithMaintenanceCostMoreOrEqual(BigDecimal maintenanceCost) {
        List<Vehicle> tasks = vehicleRepository.getVehicleWithMaintenanceCostMoreOrEqual(maintenanceCost);
        if (!tasks.isEmpty()) {
            return tasks;
        } else {
            throw new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND);
        }

    }

    @Override
    public VehicleDto create(CreateVehicleDto createVehicleDto) {
        Vehicle vehicle = mapper.toEntity(createVehicleDto);
        return mapper.toDto(vehicleRepository.save(vehicle));
    }
}
