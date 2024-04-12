package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.dto.MaintenanceDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleWithMaintenanceDto;
import de.telran.transportcompanymanagementsystem.service.interfaces.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("/{id}")
    public MaintenanceDto getMaintenanceById(@PathVariable("id") String id) {
        //http://localhost:8080/maintenance/1d8c0c4d-e54f-4ea7-b6ba-3640ddc2aa06
        return maintenanceService.getMaintenanceById(id);
    }

    @GetMapping("/cost/morethan/{maintenanceCost}")
    public List<MaintenanceDto> getMaintenanceCostMoreThan(@PathVariable("maintenanceCost") BigDecimal maintenanceCost) {
        //http://localhost:8080/maintenance/cost/morethan/500
        return maintenanceService.getMaintenanceCostMoreThan(maintenanceCost);
    }

    //@GetMapping("/vehicles-with-maintenance")
    @GetMapping("/vehicle-with-maintenance")
    public List<VehicleWithMaintenanceDto> getVehiclesWithMaintenance() {
        //http://localhost:8080/maintenance/vehicle-with-maintenance
        return maintenanceService.getVehiclesWithMaintenance();
    }
}
