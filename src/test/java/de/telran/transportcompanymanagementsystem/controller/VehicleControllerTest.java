package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleStatus;
import de.telran.transportcompanymanagementsystem.entity.enums.VehicleType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.StatusAssertions;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for VehicleController")
//@Sql("/db/changelog/changelog-master-test.xml")
//@Sql("/addTestData.sql")
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

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

        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/26e41ad9-0482-4808-9dbb-c917631f1b56"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.vehicleId", is(vehicle.getVehicleId().toString())))
                .andExpect(jsonPath("$.vehicleType", is(vehicle.getVehicleType().toString())))
                .andExpect(jsonPath("$.name", is(vehicle.getName())))
                .andExpect(jsonPath("$.model", is(vehicle.getModel())))
                .andExpect(jsonPath("$.yearManufacture", is(vehicle.getYearManufacture())))
                .andExpect(jsonPath("$.price").value(vehicle.getPrice()))
                .andExpect(jsonPath("$.vehicleStatus").value(vehicle.getVehicleStatus().toString()));
    }

    @Test
    void getAllVehicleTest() {
    }

    @Test
    void getVehicleByCarNumberTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/K234BA"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.vehicleId", is("9a04a59b-940f-4e2c-be40-5d2366b3e7b1")))
                .andExpect(jsonPath("$.carNumber", is("K234BA")));

    }
}