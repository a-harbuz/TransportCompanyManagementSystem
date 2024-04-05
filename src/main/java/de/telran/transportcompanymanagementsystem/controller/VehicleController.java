package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.service.interfaces.VehicleService;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Find vehicle by Id",
            description = "Getting vehicle by Id")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned vehicle",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))
            })
    public Vehicle getVehicleById(@PathVariable("id") String id) {
        //http://localhost:8080/vehicle/21679aa7-c43b-468d-8318-8090227c4acb
        return vehicleService.getVehicleById(id);
    }

    @GetMapping("/all")
    @Operation(
            summary = "Show all vehicles",
            description = "Getting all vehicles")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned vehicle",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Vehicle.class)))
            })
    public List<Vehicle> getAllVehicle() {
        //http://localhost:8080/vehicle/all
        return vehicleService.getAllVehicle();
    }

    @GetMapping("/carnumber/{carNumber}")
    @Operation(
            summary = "Find vehicle by car number",
            description = "Getting vehicle by car number")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned vehicle",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))
            })
    public Vehicle getVehicleByCarNumber(@PathVariable("carNumber") String carNumber) {
        //http://localhost:8080/vehicle/carnumber/AE2387KM
        return vehicleService.getVehicleByCarNumber(carNumber);
    }

    @PutMapping("/carnumber/update/{carNumber}/{newCarNumber}")
    @Operation(
            summary = "Update car number of vehicle",
            description = "Update car number of vehicle")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned vehicle",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))
            })
    public Vehicle setVehicleByCarNumber(@PathVariable("carNumber") String carNumber,
                                         @PathVariable("newCarNumber") String newCarNumber) {
        //http://localhost:8080/vehicle/carnumber/update/AE2387KM/XX7788YY
        return vehicleService.setVehicleByCarNumber(carNumber, newCarNumber);
    }

    @DeleteMapping("/carnumber/delete/{carNumber}")
    @Operation(
            summary = "Delete vehicle by car number",
            description = "Delete vehicle by car number")
    @ApiResponse(
            responseCode = "200",
            description = "return responseCode = 200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))
            })
    public void deleteVehicleByCarNumber(@PathVariable("carNumber") String carNumber) {
        //http://localhost:8080/vehicle/carnumber/delete/AE2387KM
        //http://localhost:8080/vehicle/carnumber/delete/XX7788YY
        vehicleService.deleteVehicleByCarNumber(carNumber);
    }
    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete vehicle by Id",
            description = "Delete vehicle by Id")
    @ApiResponse(
            responseCode = "200",
            description = "return responseCode = 200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))
            })
    public void deleteVehicleById(@PathVariable("id") String carNumber) {
        //http://localhost:8080/vehicle/delete/26e41ad9-0482-4808-9dbb-c917631f1b56
        vehicleService.deleteVehicleById(carNumber);
    }

    @PostMapping("/new")
    @Operation(
            summary = "Delete vehicle by Id",
            description = "Delete vehicle by Id")
    @ApiResponse(
            responseCode = "201",
            description = "Successfully returned vehicle",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Vehicle.class))
            })
    public Vehicle createVehicle(@RequestBody Vehicle vehicle)
    {
        //http://localhost:8080/vehicle/new
        return vehicleService.create(vehicle);
    }
}
