package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import util.EntityCreator;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
        Vehicle vehicle = EntityCreator.getVehicle();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/26e41ad9-0482-4808-9dbb-c917631f1b56"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.vehicleId", is(vehicle.getVehicleId().toString())))
                .andExpect(jsonPath("$.vehicleType", is(vehicle.getVehicleType().toString())))
                .andExpect(jsonPath("$.name", is(vehicle.getName())))
                .andExpect(jsonPath("$.model", is(vehicle.getModel())))
                .andExpect(jsonPath("$.yearManufacture", is(vehicle.getYearManufacture())))
                .andExpect(jsonPath("$.carNumber", is(vehicle.getCarNumber())))
                .andExpect(jsonPath("$.price").value(vehicle.getPrice()))
                .andExpect(jsonPath("$.vehicleStatus").value(vehicle.getVehicleStatus().toString()));
    }

    @Test
    void getAllVehicleTest() throws Exception {
        Vehicle vehicle = EntityCreator.getVehicle();
        Vehicle vehicle2 = EntityCreator.getVehicle2();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/all"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[1].vehicleId", is(vehicle.getVehicleId().toString())))
                .andExpect(jsonPath("$[1].vehicleType", is(vehicle.getVehicleType().toString())))
                .andExpect(jsonPath("$[1].name", is(vehicle.getName())))
                .andExpect(jsonPath("$[1].model", is(vehicle.getModel())))
                .andExpect(jsonPath("$[1].yearManufacture", is(vehicle.getYearManufacture())))
                .andExpect(jsonPath("$[1].carNumber", is(vehicle.getCarNumber())))
                .andExpect(jsonPath("$[1].price").value(vehicle.getPrice()))
                .andExpect(jsonPath("$[1].vehicleStatus").value(vehicle.getVehicleStatus().toString()))

                .andExpect(jsonPath("$[2].vehicleId", is(vehicle2.getVehicleId().toString())))
                .andExpect(jsonPath("$[2].vehicleType", is(vehicle2.getVehicleType().toString())))
                .andExpect(jsonPath("$[2].name", is(vehicle2.getName())))
                .andExpect(jsonPath("$[2].carNumber", is(vehicle2.getCarNumber())))
                .andExpect(jsonPath("$[2].vehicleStatus").value(vehicle2.getVehicleStatus().toString()));
    }

    @Test
    void getVehicleByCarNumberTest() throws Exception {
        Vehicle vehicle = EntityCreator.getVehicle2();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/K234BA"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.vehicleId", is(vehicle.getVehicleId().toString())))
                .andExpect(jsonPath("$.carNumber", is(vehicle.getCarNumber())));
    }

    @Test
    void setVehicleByCarNumber() throws Exception {
        Vehicle vehicle = EntityCreator.getVehicle();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/update/AE2387KM/XX7788YY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleId", is(vehicle.getVehicleId().toString())))
                .andExpect(jsonPath("$.carNumber", is("XX7788YY")));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/update/XX7788YY/AE2387KM"))
                .andExpect(status().isOk());
    }

    @Test
    void deleteVehicleByCarNumber() {
    }

    @Test
    void deleteVehicleById() {
    }

    @Test
    void saveVehicle() {
    }
}