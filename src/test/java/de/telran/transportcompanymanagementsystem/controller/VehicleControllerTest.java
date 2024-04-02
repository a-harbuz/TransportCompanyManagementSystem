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
    void getAllVehicleTest() throws Exception {
        String uuidPattern = CheckUuidPattern.getUuidPattern();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].vehicleId", matchesPattern(uuidPattern)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[2].vehicleId", matchesPattern(uuidPattern)));
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
    void setVehicleByCarNumber() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.put("/vehicle/carnumber/update/SC1238KM/XX7788YY"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.vehicleId", is("21679aa7-c43b-468d-8318-8090227c4acb")))
                .andExpect(jsonPath("$.carNumber", is("XX7788YY")));
        mockMvc
                .perform(MockMvcRequestBuilders.put("/vehicle/carnumber/update/XX7788YY/SC1238KM"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carNumber", is("SC1238KM")));
    }

    @Test
    void deleteVehicleByCarNumber() throws Exception {
        Vehicle vehicle = EntityCreator.getNewVehicle();
        vehicle.setCarNumber("NUMBER_FOR_DEL");
        String requestBody = objectMapper.writeValueAsString(vehicle);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/vehicle/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/" + vehicle.getCarNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.carNumber", is(vehicle.getCarNumber())));
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/vehicle/carnumber/delete/" + vehicle.getCarNumber()))
                .andExpect(status().isOk());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/" + vehicle.getCarNumber()))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteVehicleById() throws Exception {
        Vehicle vehicle = EntityCreator.getNewVehicle();
        vehicle.setCarNumber("NUMBER2_FOR_DEL");
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
    void createVehicle() throws Exception {
        Vehicle newVehicle = EntityCreator.getNewVehicle();
        String requestBody = objectMapper.writeValueAsString(newVehicle);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/vehicle/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.carNumber", is(newVehicle.getCarNumber())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.price").value(newVehicle.getPrice()));

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/vehicle/carnumber/delete/" + newVehicle.getCarNumber()))
                .andExpect(status().isOk());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/vehicle/carnumber/" + newVehicle.getCarNumber()))
                .andExpect(status().isNotFound());
    }
}