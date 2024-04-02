package de.telran.transportcompanymanagementsystem.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.telran.transportcompanymanagementsystem.entity.Task;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for TaskController")
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void getTaskByWaybillNumberTest() throws Exception {
        Task expected = EntityCreator.getTask();
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/task/waybillnumber/004")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        String mvcResultJson = mvcResult.getResponse().getContentAsString();
        Task actual = objectMapper.readValue(mvcResultJson, Task.class);
        assertEquals(expected, actual);
    }

    @Test
    void getTaskByWeightCargoWhenMoreThanTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/task/weightcargo/morethan/10000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].taskId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[1].taskId", matchesPattern(CheckUuidPattern.getUuidPattern())));
    }
}