package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import util.CheckUuidPattern;
import util.EntityCreator;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for EmployeeController")
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getEmployeeById() throws Exception {
        Employee expected = EntityCreator.getEmployee();
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/" + expected.getEmployeeId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        Employee actual = objectMapper.readValue(mvcResultJson, Employee.class);
        assertEquals(expected, actual);
    }

    @Test
    void getEmployeeList() throws Exception {
        String uuidPattern = CheckUuidPattern.getUuidPattern();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[0].employeeId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[6].employeeId", matchesPattern(uuidPattern)));
    }

    @Test
    void getDriversList() throws Exception {
        String uuidPattern = CheckUuidPattern.getUuidPattern();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/drivers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].employeeId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[0].driver").value(true))
                .andExpect(jsonPath("$[3].employeeId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[3].driver").value(true));
    }

    @Test
    void getEmployeeWithOneOrMoreVehicleMaintenanceTest() throws Exception {
        String uuidPattern = CheckUuidPattern.getUuidPattern();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employee/with-vehicle-maintenance"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].employeeId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[0].firstName").isNotEmpty())
                .andExpect(jsonPath("$[0].lastName").isNotEmpty())
                .andExpect(jsonPath("$[3].employeeId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[3].firstName").isNotEmpty())
                .andExpect(jsonPath("$[3].lastName").isNotEmpty());
    }

//    @Test
//    void createEmployee() {
////        EmployeeRegistrationDto employeeRegistrationDto = EntityCreator.getNewEmployeeRegistration();
////        String requestBody = objectMapper.writeValueAsString(employeeRegistrationDto);
////        MvcResult mvcResult = mockMvc
////                .perform(MockMvcRequestBuilders.post("/employee/registration")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(requestBody))
////                .andExpect(status().isOk())
////                .andExpect(jsonPath("$.operation", is("EMPLOYEE CREATION")))
////                .andExpect(jsonPath("$.status", is("CREATED")))
////                .andExpect(jsonPath("$.login", is(employeeRegistrationDto.getLogin())))
////                .andReturn();
////        //repeat throw
//    }
}