package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.exception.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.VehicleRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Override
    public Vehicle getVehicleById(String id) {
        try {
            return vehicleRepository.findById(UUID.fromString(id)).orElseThrow(()-> new UserPrincipalNotFoundException(ErrorMessage.VEHICLE_NOT_FOUND));
        } catch (UserPrincipalNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
