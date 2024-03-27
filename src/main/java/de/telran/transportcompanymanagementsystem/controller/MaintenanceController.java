package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import de.telran.transportcompanymanagementsystem.service.interfaces.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("/{id}")
    public Maintenance getMaintenanceById(@PathVariable("id") String id) {
        return maintenanceService.getMaintenanceById(id);
    }

    @GetMapping("/cost/morethan/{maintenanceCost}")
    public List<Maintenance> getMaintenanceCostMoreThan(@PathVariable("maintenanceCost") BigDecimal maintenanceCost) {
        return maintenanceService.getMaintenanceCostMoreThan(maintenanceCost);
    }
}
