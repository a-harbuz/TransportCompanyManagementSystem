package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.entity.Contract;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import util.EntityCreator;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for ContractController")
class ContractControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    void getContractByIdTest() throws Exception {
        Contract contract = EntityCreator.getContract();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/contract/c8e0d900-fcd7-4182-925c-fb3a8d010243"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contractId", is(contract.getContractId().toString())))
                .andExpect(jsonPath("$.contractNumber", is(contract.getContractNumber())))
                .andExpect(jsonPath("$.contractName", is(contract.getContractName())))
                .andExpect(jsonPath("$.costTransportationUnderContract").value(contract.getCostTransportationUnderContract()))
                .andExpect(jsonPath("$.totalCostTransportedGoods").value(contract.getTotalCostTransportedGoods()))
                .andExpect(jsonPath("$.contractStatus", is(contract.getContractStatus().toString())));
    }
}