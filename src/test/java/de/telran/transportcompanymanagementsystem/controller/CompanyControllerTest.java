package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Company;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.security.test.context.support.WithMockUser;
import util.CheckUuidPattern;
import util.EntityCreator;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for CompanyController")
@WithMockUser(username = "admin", password = "1234", roles = "DEVELOPER")
class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getCompanyByIdTest() throws Exception {
        Company company = EntityCreator.getCompany();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/" + company.getCompanyId().toString()))
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
    void getCompanyByIdNegativeTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/0a8de57b-4ac3-43f9-9ab4-77784de2554b"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getCompanyByNameTest() throws Exception {
        Company company = EntityCreator.getCompany();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/name/Boehm-Klein"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].companyName", is(company.getCompanyName())))
                .andExpect(jsonPath("$[0].contactFirstName", is(company.getContactFirstName())))
                .andExpect(jsonPath("$[0].contactLastName", is(company.getContactLastName())));
    }

    @Test
    void getCompaniesTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[0].companyName").isNotEmpty())
                .andExpect(jsonPath("$[2].companyId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[2].companyName").isNotEmpty());
    }

    @Test
    void deleteCompanyByIdTest() throws Exception {
        Company company = EntityCreator.getNewCompany();
        company.setCompanyName("TEST_NAME_FOR_DELETE");
        String requestBody = objectMapper.writeValueAsString(company);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/company/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        Company newCompany = objectMapper.readValue(mvcResultJson, Company.class);

        mockMvc
                .perform(MockMvcRequestBuilders.delete("/company/delete/" + newCompany.getCompanyId()))
                .andExpect(status().isOk());
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/" + newCompany.getCompanyId()))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCompanyTest() throws Exception {
        Company newCompany = EntityCreator.getNewCompany();
        String requestBody = objectMapper.writeValueAsString(newCompany);
        mockMvc
                .perform(MockMvcRequestBuilders.post("/company/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.companyId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.companyName", is(newCompany.getCompanyName())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$.phone", is(newCompany.getPhone())));
    }

    @Test
    void getCompanyByNameIncludingString() throws Exception {
        Company company = EntityCreator.getCompany();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/company/name/" + company.getCompanyName()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$[0].companyId", is(company.getCompanyId().toString())))
                .andExpect(jsonPath("$[0].companyName", is(company.getCompanyName())))
                .andExpect(jsonPath("$[0].contactFirstName", is(company.getContactFirstName())))
                .andExpect(jsonPath("$[0].contactLastName", is(company.getContactLastName())))
                .andExpect(jsonPath("$[0].email", is(company.getEmail())))
                .andExpect(jsonPath("$[0].address", is(company.getAddress())))
                .andExpect(jsonPath("$[0].phone", is(company.getPhone())));
    }

    @Test
    void updateCompanyById() throws Exception {
        Company expectedCompany = new Company();
        expectedCompany.setCompanyId(UUID.fromString("875eeb54-d18a-4cfc-8efc-d984c8c362ff"));
        expectedCompany.setCompanyName("TEST_NAME_FOR_UPDATE");
        expectedCompany.setAddress("TEST_ADDRESS_FOR_UPDATE");
        expectedCompany.setPhone("12344321");
        String requestBody = objectMapper.writeValueAsString(expectedCompany);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.put("/company/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        Company actualCompany = objectMapper.readValue(mvcResultJson, Company.class);
        assertEquals(expectedCompany.getCompanyName(), actualCompany.getCompanyName());
        assertEquals(expectedCompany.getAddress(), actualCompany.getAddress());
        assertEquals(expectedCompany.getPhone(), actualCompany.getPhone());
    }

    @Test
    void updateCompanyByName() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.put("/company/update/name/Larson-Witting/New Larson"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("New Larson")));
        mockMvc
                .perform(MockMvcRequestBuilders.put("/company/update/name/New Larson/Larson-Witting"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName", is("Larson-Witting")));
    }
}