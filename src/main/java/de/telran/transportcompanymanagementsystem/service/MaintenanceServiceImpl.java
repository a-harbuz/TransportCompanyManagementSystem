package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.MaintenanceDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleWithMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.MaintenanceNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.MaintenanceMapper;
import de.telran.transportcompanymanagementsystem.repository.MaintenanceRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MaintenanceServiceImpl implements MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final MaintenanceMapper maintenanceMapper;

    @Override
    public MaintenanceDto getMaintenanceById(String id) {
        return maintenanceMapper.toDto(maintenanceRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND)));
    }

    @Override
    public List<MaintenanceDto> getMaintenanceList() {
        List<Maintenance> maintenanceList = maintenanceRepository.findAll();
        if (maintenanceList.isEmpty()) throw new MaintenanceNotFoundException(ErrorMessage.MAINTENANCE_NOT_FOUND);
        return maintenanceMapper.toDtoList(maintenanceList);
    }

    @Override
    public List<VehicleWithMaintenanceDto> getMaintenanceWithVehiclesAndCompany() {
        List<Maintenance> maintenanceList = maintenanceRepository.findAll();
        if (maintenanceList.isEmpty()) throw new MaintenanceNotFoundException(ErrorMessage.MAINTENANCE_NOT_FOUND);
        return maintenanceMapper.toVehicleWithMaintenanceDto(maintenanceList);
    }

    @Override
    public List<MaintenanceDto> getMaintenanceCostMoreThan(BigDecimal maintenanceCost) {
        return maintenanceMapper.toDtoList(
                maintenanceRepository.getMaintenanceByMaintenanceCostGreaterThan(maintenanceCost));
    }
}
