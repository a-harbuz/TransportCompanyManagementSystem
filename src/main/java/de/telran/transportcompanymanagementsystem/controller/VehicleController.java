package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.service.interfaces.VehicleService;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/carnumber/{carNumber}")
    public Vehicle getVehicleByCarNumber(@PathVariable("carNumber") String carNumber) {
        //http://localhost:8080/vehicle/carnumber/AE2387KM
        return vehicleService.getVehicleByCarNumber(carNumber);
    }

    @GetMapping("/carnumber/update/{carNumber}/{newCarNumber}")
    public Vehicle setVehicleByCarNumber(@PathVariable("carNumber") String carNumber,
                                         @PathVariable("newCarNumber") String newCarNumber) {
        //http://localhost:8080/vehicle/carnumber/update/AE2387KM/XX7788YY
        return vehicleService.setVehicleByCarNumber(carNumber, newCarNumber);
    }
}
