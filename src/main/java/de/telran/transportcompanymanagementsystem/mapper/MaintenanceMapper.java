package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.MaintenanceDto;
import de.telran.transportcompanymanagementsystem.dto.VehicleWithMaintenanceDto;
import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, imports = Timestamp.class,
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MaintenanceMapper {
    MaintenanceDto toDto(Maintenance maintenance);

    List<MaintenanceDto> toDtoList(List<Maintenance> maintenances);

    List<VehicleWithMaintenanceDto> toVehicleWithMaintenanceDto (List<Maintenance> maintenance);
    @AfterMapping
    default void getVehicleInfo(@MappingTarget VehicleWithMaintenanceDto vehicleWithMaintenanceDto,
                                Maintenance maintenance){
        vehicleWithMaintenanceDto.setMaintenanceDate(maintenance.getCreatedAt());
        vehicleWithMaintenanceDto.setVehicleId(maintenance.getVehicle().getVehicleId());
        vehicleWithMaintenanceDto.setVehicleType(maintenance.getVehicle().getVehicleType());
        vehicleWithMaintenanceDto.setName(maintenance.getVehicle().getName());
        vehicleWithMaintenanceDto.setModel(maintenance.getVehicle().getModel());
        vehicleWithMaintenanceDto.setYearManufacture(maintenance.getVehicle().getYearManufacture());
        vehicleWithMaintenanceDto.setCarNumber(maintenance.getVehicle().getCarNumber());
        vehicleWithMaintenanceDto.setInitialKilometers(maintenance.getVehicle().getInitialKilometers());
        vehicleWithMaintenanceDto.setCompanyName(maintenance.getCompany().getCompanyName());
    }
}
