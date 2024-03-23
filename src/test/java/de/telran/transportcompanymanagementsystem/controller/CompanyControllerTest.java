package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Company;
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
@DisplayName("Test class for CompanyController")
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void getCompanyById() throws Exception {
        Company company = EntityCreator.getCompany();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/0a8de57b-4ac3-43f9-9ab4-77784de2554a"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.companyId", is(company.getCompanyId().toString())))
                .andExpect(jsonPath("$.companyName", is(company.getCompanyName())))
                .andExpect(jsonPath("$.contactFirstName", is(company.getContactFirstName())))
                .andExpect(jsonPath("$.contactLastName", is(company.getContactLastName())))
                .andExpect(jsonPath("$.email", is(company.getEmail())))
                .andExpect(jsonPath("$.address", is(company.getAddress())))
                .andExpect(jsonPath("$.phone", is(company.getPhone())));
    }

    @Test
    void getCompanyByName() throws Exception {
        Company company = EntityCreator.getCompany();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/name/Boehm-Klein"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].companyId", is(company.getCompanyId().toString())))
                .andExpect(jsonPath("$[0].companyName", is(company.getCompanyName())));
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/name/Larson"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].companyId", is("2d0cc985-ffdc-40de-be58-69eba564fc47")))
                .andExpect(jsonPath("$[0].companyName", is("Larson-Witting")));
    }
}