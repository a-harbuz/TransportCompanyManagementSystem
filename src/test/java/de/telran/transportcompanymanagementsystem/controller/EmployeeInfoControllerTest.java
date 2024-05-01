package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
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
import util.CheckUuidPattern;
import util.EntityCreator;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.matchesPattern;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for EmployeeInfoController")
@WithMockUser(username = "admin", password = "1234", roles = "DEVELOPER")
class EmployeeInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getEmployeeInfoById() throws Exception {
        EmployeeInfo expected = EntityCreator.getEmployeeInfo();
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/employeeinfo/" + expected.getEmployeeInfoId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        EmployeeInfo actual = objectMapper.readValue(mvcResultJson, EmployeeInfo.class);
        assertEquals(expected, actual);
    }

    @Test
    void getEmployeeInfoListTest() throws Exception {
        String uuidPattern = CheckUuidPattern.getUuidPattern();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employeeinfo/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(7)))
                .andExpect(jsonPath("$[0].employeeInfoId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[6].employeeInfoId", matchesPattern(uuidPattern)));
    }

    @Test
    void getEmployeeInfoNoDriverLicense() throws Exception {
        String uuidPattern = CheckUuidPattern.getUuidPattern();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/employeeinfo/noDriverLicense"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].employeeInfoId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[0].drivingLicenseCategory").value(DrivingLicenseCategory.ABSENT.toString()))
                .andExpect(jsonPath("$[1].employeeInfoId", matchesPattern(uuidPattern)))
                .andExpect(jsonPath("$[1].drivingLicenseCategory").value(DrivingLicenseCategory.ABSENT.toString()));
    }
}