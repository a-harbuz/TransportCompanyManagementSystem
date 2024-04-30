package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.annotation.vehicle.*;
import de.telran.transportcompanymanagementsystem.dto.CreateVehicleDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleDto;
import de.telran.transportcompanymanagementsystem.service.interfaces.VehicleService;
import de.telran.transportcompanymanagementsystem.validation.UuidChecker;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Validated
@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetVehicleByIdDocumentation(path = "/{id}")
    public VehicleDto getVehicleById(@PathVariable("id") @UuidChecker String id) {
        //http://localhost:8080/vehicle/21679aa7-c43b-468d-8318-8090227c4acb
        return vehicleService.getVehicleById(id);
    }

    @GetVehicleListDocumentation(path = "/all")
    public List<VehicleDto> getVehicleList() {
        //http://localhost:8080/vehicle/all
        return vehicleService.getAllVehicle();
    }

    @GetVehicleByCarNumberDocumentation(path = "/carnumber/{carNumber}")
    public VehicleDto getVehicleByCarNumber(@PathVariable("carNumber") String carNumber) {
        //http://localhost:8080/vehicle/carnumber/AE2387KM
        return vehicleService.getVehicleByCarNumber(carNumber);
    }

    @DeleteVehicleByIdDocumentation(path = "/delete/{id}")
    public void deleteVehicleById(@PathVariable("id") String id) {
        //http://localhost:8080/vehicle/26e41ad9-0482-4808-9dbb-c917631f1b56
        vehicleService.deleteVehicleById(id);
    }

    @CreateVehicleDocumentation(path = "/new")
    public VehicleDto createVehicle(@RequestBody CreateVehicleDto createVehicleDto) {
        //http://localhost:8080/vehicle/new
        return vehicleService.create(createVehicleDto);
    }

    @GetVehicleWithMaintenanceCostMoreOrEqualDocumentation(path = "/maintenancecost/{maintenanceCost}" )
    public List<VehicleDto> getVehicleWithMaintenanceCostMoreOrEqual(@PathVariable("maintenanceCost")
            BigDecimal maintenanceCost) {
        //http://localhost:8080/vehicle/maintenancecost/500
        return vehicleService.getVehicleWithMaintenanceCostMoreOrEqual(maintenanceCost);
    }
}
