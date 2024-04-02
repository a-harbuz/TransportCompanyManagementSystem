package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Contract;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import util.EntityCreator;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
                .perform(MockMvcRequestBuilders.get("/contract/" + contract.getContractId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contractId", is(contract.getContractId().toString())))
                .andExpect(jsonPath("$.contractNumber", is(contract.getContractNumber())))
                .andExpect(jsonPath("$.contractName", is(contract.getContractName())))
                .andExpect(jsonPath("$.costTransportationUnderContract").value(contract.getCostTransportationUnderContract()))
                .andExpect(jsonPath("$.totalCostTransportedGoods").value(contract.getTotalCostTransportedGoods()))
                .andExpect(jsonPath("$.contractStatus", is(contract.getContractStatus().toString())));
    }

    @Test
    void getContractByIdNegativeTest() {
        assertThrows(ServletException.class, () ->
                mockMvc.perform(MockMvcRequestBuilders.get("/contract/0a8de57b-4ac3-43f9-9ab4-77784de2554c"))
                .andExpect(status().isInternalServerError()));
    }

    @Test
    void getContractByContractNumberTest() throws Exception {
        Contract contract = EntityCreator.getContract();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/contract/number/" + contract.getContractNumber()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.contractId", is(contract.getContractId().toString())))
                .andExpect(jsonPath("$.contractNumber", is(contract.getContractNumber())))
                .andExpect(jsonPath("$.contractName", is(contract.getContractName())))
                .andExpect(jsonPath("$.costTransportationUnderContract")
                        .value(contract.getCostTransportationUnderContract()))
                .andExpect(jsonPath("$.totalCostTransportedGoods")
                        .value(contract.getTotalCostTransportedGoods()))
                .andExpect(jsonPath("$.contractStatus", is(contract.getContractStatus().toString())));
    }
    @Test
    void getContractByContractNumberNegativeTest() {
        assertThrows(ServletException.class, () ->
                mockMvc.perform(MockMvcRequestBuilders.get("/contract/number/0011"))
                .andExpect(status().isInternalServerError()));
    }
}