package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.VehicleDto;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleStatus;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;
import de.telran.transportcompanymanagementsystem.mapper.VehicleMapper;
import de.telran.transportcompanymanagementsystem.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import util.EntityCreator;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VehicleServiceImplTest {

    private static final String TEST_ID = "26e41ad9-0482-4808-9dbb-c917631f1b56";
    @InjectMocks
    private VehicleServiceImpl vehicleService;
    @Mock
    private VehicleRepository vehicleRepositoryMock;
    @Mock
    private VehicleMapper vehicleMapperMock;

    @Test
    void getVehicleById() {
        VehicleDto expectedVehicleDto = VehicleDto.builder()
                .vehicleId(UUID.fromString(TEST_ID))
                .vehicleType(VehicleType.TRUCK20T)
                .name("MAN")
                .model("TGS 35.480")
                .yearManufacture("2008")
                .carNumber("AE2387KM")
                .initialKilometers(52300)
                .price(50000)
                .vehicleStatus(VehicleStatus.WORKING)
                .build();

        when(vehicleRepositoryMock.findById(any(UUID.class)))
                .thenReturn(Optional.of(EntityCreator.getVehicle()));
        when(vehicleMapperMock.toDto(any(Vehicle.class))).thenReturn(expectedVehicleDto);

        VehicleDto actualVehicleDto = vehicleService.getVehicleById(TEST_ID);
        assertEquals(expectedVehicleDto, actualVehicleDto);
    }

    @Test
    void getVehicleByCarNumber() {
        VehicleDto expectedVehicleDto = VehicleDto.builder()
                .vehicleId(UUID.fromString(TEST_ID))
                .vehicleType(VehicleType.TRUCK20T)
                .name("MAN")
                .model("TGS 35.480")
                .yearManufacture("2008")
                .carNumber("AE2387KM")
                .initialKilometers(52300)
                .price(50000)
                .vehicleStatus(VehicleStatus.WORKING)
                .build();

        when(vehicleRepositoryMock.findByCarNumber(anyString())).thenReturn(EntityCreator.getVehicle());
        when(vehicleMapperMock.toDto(any(Vehicle.class))).thenReturn(expectedVehicleDto);

        VehicleDto actualVehicleDto = vehicleService.getVehicleByCarNumber("AE2387KM");
        assertEquals(expectedVehicleDto, actualVehicleDto);
    }
}