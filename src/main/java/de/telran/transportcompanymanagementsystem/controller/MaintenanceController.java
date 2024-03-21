package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import de.telran.transportcompanymanagementsystem.service.interfaces.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @GetMapping("/{id}")
    public Maintenance getMaintenanceById(@PathVariable("id") String id) {
        //http://localhost:8080/vehicle/21679aa7-c43b-468d-8318-8090227c4acb
        return maintenanceService.getMaintenanceById(id);
    }
}
