package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleStatus;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
//@Sql("/db/changelog/changelog-master-test.xml")
//@Sql("/addTestData.sql")
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getVehicleByIdTest() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(UUID.fromString("26e41ad9-0482-4808-9dbb-c917631f1b56"));
        vehicle.setVehicleType(VehicleType.TRUCK20T);
        vehicle.setName("MAN");
        vehicle.setModel("TGS 35.480");
        vehicle.setYearManufacture("2008");
        vehicle.setCarNumber("AE2387KM");
        vehicle.setInitialKilometers(52300);
        vehicle.setPrice(50000);
        vehicle.setVehicleStatus(VehicleStatus.WORKING);
        vehicle.setCreatedAt(Timestamp.valueOf("2024-01-14" + " 00:00:00"));

        String vehicleJson = objectMapper.writeValueAsString(Vehicle.class);
        //String vehicleJson = objectMapper.writeValueAsString(vehicle);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(vehicleJson);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        MvcResult createVehicleResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/26e41ad9-0482-4808-9dbb-c917631f1b56")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(vehicleJson))
                .andReturn();

        //Получает JSON из ответа
        String vehicleResultJSON = createVehicleResult.getResponse().getContentAsString();
        //Получает Clsss из JSON
        //Vehicle vehicleResult = objectMapper.readValue(vehicleResultJSON, Vehicle.class);

        Assertions.assertEquals(200, createVehicleResult.getResponse().getStatus());
        //Assertions.assertEquals(vehicle.getName(), vehicleResult.getName());


    }

    @Test
    void getAllVehicleTest() {
    }

    @Test
    void getVehicleByCarNumberTest() {
    }
}