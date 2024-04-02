package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Maintenance;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for MaintenanceController")
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
}