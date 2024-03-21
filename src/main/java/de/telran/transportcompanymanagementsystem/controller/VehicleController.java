package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.service.interfaces.VehicleService;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/{id}")
    public Vehicle getVehicleById(@PathVariable("id") String id) {
        //http://localhost:8080/vehicle/21679aa7-c43b-468d-8318-8090227c4acb
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/all")
    public List<Vehicle> getAllVehicle() {
        //http://localhost:8080/vehicle/all
        return vehicleService.getAllVehicle();
    }

}
