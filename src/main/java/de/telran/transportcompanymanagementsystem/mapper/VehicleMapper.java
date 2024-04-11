package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.CreateVehicleDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleDto;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.mapstruct.*;

import java.sql.Timestamp;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface VehicleMapper {
    VehicleDto toDto(Vehicle vehicle);
    @Mappings({
            @Mapping(target = "createdAt", expression = "java(new Timestamp(System.currentTimeMillis()))")
    })
    Vehicle toEntity (CreateVehicleDto createVehicleDto);
}
