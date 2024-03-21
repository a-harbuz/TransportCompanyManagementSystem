package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errorMessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.MaintenanceRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    @Override
    public Maintenance getMaintenanceById(String id) {
        return maintenanceRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
    }
}
