package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.CheckUuidPattern;
import util.EntityCreator;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for VehicleController")
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getVehicleByIdTest() throws Exception {
        Vehicle vehicle = EntityCreator.getVehicle();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/" + vehicle.getVehicleId()))
                .andExpect(status().isOk())
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
    void getVehicleByIdNegativeTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/26e41ad9-0482-4808-9dbb-c917631f1b5a"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getVehicleListTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[2].vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())));
    }

    @Test
    void getVehicleByCarNumberTest() throws Exception {
        Vehicle vehicle = EntityCreator.getVehicle();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/AE2387KM"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.vehicleId", is(vehicle.getVehicleId().toString())))
                .andExpect(jsonPath("$.carNumber", is(vehicle.getCarNumber())));
    }

    @Test
    void deleteVehicleByIdTest() throws Exception {
        Vehicle vehicle = EntityCreator.getNewVehicle();
        vehicle.setCarNumber("NUM_FOR_DEL");
        String requestBody = objectMapper.writeValueAsString(vehicle);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/vehicle/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        Vehicle newVehicle = objectMapper.readValue(mvcResultJson, Vehicle.class);

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/vehicle/delete/" + newVehicle.getVehicleId()))
                .andExpect(status().isOk());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/" + newVehicle.getVehicleId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void createVehicleTest() throws Exception {
        Vehicle newVehicle = EntityCreator.getNewVehicle();
        String requestBody = objectMapper.writeValueAsString(newVehicle);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/vehicle/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.carNumber", is(newVehicle.getCarNumber())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.price").value(newVehicle.getPrice()))
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        Vehicle actualVehicle = objectMapper.readValue(mvcResultJson, Vehicle.class);
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/vehicle/delete/" + actualVehicle.getVehicleId()))
                .andExpect(status().isOk());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/" + actualVehicle.getVehicleId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void getVehicleWithMaintenanceCostMoreOrEqual() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/maintenancecost/500"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[2].vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())));
    }
}