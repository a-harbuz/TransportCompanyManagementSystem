package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Task;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import util.EntityCreator;

import java.math.BigDecimal;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for TaskController")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getTaskByIdTest() throws Exception {
        Task task = EntityCreator.getTask();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/task/" + task.getTaskId()))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.taskId", is(task.getTaskId().toString())))
                .andExpect(jsonPath("$.addressFrom", is(task.getAddressFrom())))
                .andExpect(jsonPath("$.addressTo", is(task.getAddressTo())))
                .andExpect(jsonPath("$.waybillNumber", is(task.getWaybillNumber())))
                .andExpect(jsonPath("$.waybillCost").value(task.getWaybillCost()));
    }

    @Test
    void getTaskByWaybillNumberTest() {

    }

    @Test
    void getTaskByWeightCargoWhenMoreThanTest() {

    }
}