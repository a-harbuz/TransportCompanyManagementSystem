package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Maintenance;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.CheckUuidPattern;
import util.EntityCreator;
import util.EnumsToArrays;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for MaintenanceController")
@WithMockUser(username = "admin", password = "1234", roles = "DEVELOPER")
class MaintenanceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getMaintenanceById() throws Exception {
        Maintenance expected = EntityCreator.getMaintenance();
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/maintenance/1d8c0c4d-e54f-4ea7-b6ba-3640ddc2aa06")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        Maintenance actual = objectMapper.readValue(mvcResultJson, Maintenance.class);
        assertEquals(expected, actual);
    }

    @Test
    void getMaintenanceCostMoreThan() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/maintenance/cost/morethan/500.99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].mid", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].mid", matchesPattern(CheckUuidPattern.getUuidPattern())));
    }

    @Test
    void getMaintenanceWithVehiclesAndCompany() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/maintenance/with-vehicle-and-company"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[0].serviceType").value(in(EnumsToArrays.getArrayOfServiceType())))
                .andExpect(jsonPath("$[0].companyName").isNotEmpty())
                .andExpect(jsonPath("$[3].vehicleId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[3].serviceType").value(in(EnumsToArrays.getArrayOfServiceType())))
                .andExpect(jsonPath("$[0].companyName").isNotEmpty());
    }

    @Test
    void getMaintenanceList() throws Exception  {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/maintenance/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].mid", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[0].serviceType").value(in(EnumsToArrays.getArrayOfServiceType())))
                .andExpect(jsonPath("$[3].mid", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[3].serviceType").value(in(EnumsToArrays.getArrayOfServiceType())));
    }
}