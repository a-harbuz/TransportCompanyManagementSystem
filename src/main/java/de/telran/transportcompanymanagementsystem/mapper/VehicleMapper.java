package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.CreateVehicleDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleDto;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface VehicleMapper {
    VehicleDto toDto(Vehicle vehicle);

    List<VehicleDto> toDtoList(List<Vehicle> vehicles);

    @Mappings({
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))"),
            @Mapping(target = "vehicleId", ignore = true),
            @Mapping(target = "maintenances", ignore = true),
            @Mapping(target = "tasks", ignore = true)
    })
    Vehicle toEntity (CreateVehicleDto createVehicleDto);
}
