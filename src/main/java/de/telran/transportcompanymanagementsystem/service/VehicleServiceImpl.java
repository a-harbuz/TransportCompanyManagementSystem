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
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;

    @Override
    public VehicleDto getVehicleById(String id) {
        return vehicleMapper.toDto(vehicleRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND)));
    }

    @Override
    public List<VehicleDto> getAllVehicle() {
        return vehicleMapper.toDtoList(vehicleRepository.findAll());
    }

    @Override
    public VehicleDto getVehicleByCarNumber(String carNumber) {
        Vehicle vehicle = vehicleRepository.findByCarNumber(carNumber);
        if (vehicle != null) {
            return vehicleMapper.toDto(vehicle);
        } else {
            throw new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND_BY_CAR_NUMBER);
        }
    }

    @Override
    @Transactional
    public void deleteVehicleById(String id) {
        vehicleRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND));
        vehicleRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public List<VehicleDto> getVehicleWithMaintenanceCostMoreOrEqual(BigDecimal maintenanceCost) {
        List<Vehicle> tasks = vehicleRepository.getVehicleWithMaintenanceCostMoreOrEqual(maintenanceCost);
        if (!tasks.isEmpty()) {
            return vehicleMapper.toDtoList(tasks);
        } else {
            throw new VehicleNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND);
        }

    }

    @Override
    @Transactional
    public VehicleDto create(CreateVehicleDto createVehicleDto) {
        Vehicle vehicle = vehicleMapper.toEntity(createVehicleDto);
        return vehicleMapper.toDto(vehicleRepository.save(vehicle));
    }
}
