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
                .andExpect(jsonPath("$[0].taskId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[1].taskId", matchesPattern(CheckUuidPattern.getUuidPattern())));
    }

    @Test
    void getTaskForDriverByWaybillNumberTest() throws Exception {
        Task expectedTask = EntityCreator.getTask();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/task/fordriver/waybillnumber/004"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transportationDate",
                        is(expectedTask.getTransportationDate().toLocalDate().toString())))
                .andExpect(jsonPath("$.addressFrom", is(expectedTask.getAddressFrom())))
                .andExpect(jsonPath("$.addressTo", is(expectedTask.getAddressTo())))
                .andExpect(jsonPath("$.waybillNumber", is(expectedTask.getWaybillNumber())));
    }

    @Test
    void getTasksByCompanyNameAndWaybillCostMoreThanTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/task/companyname-waybillcost/Boehm-Klein/9000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].taskId", matchesPattern(CheckUuidPattern.getUuidPattern())))
                .andExpect(jsonPath("$[0].waybillNumber", is("003")))
                .andExpect(jsonPath("$[0].waybillCost", greaterThan(9000.00)));
    }

    @Test
    void createTaskTest() throws Exception {
//        TaskDto newTaskDto = EntityCreator.getNewTask();
//        String requestBody = objectMapper.writeValueAsString(newTaskDto);
//        mockMvc
//                .perform(MockMvcRequestBuilders.post("/task/new")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(requestBody))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.taskId", matchesPattern(CheckUuidPattern.getUuidPattern())))
//                .andExpect(jsonPath("$.addressFrom", is(newTaskDto.getAddressFrom())))
//                .andExpect(jsonPath("$.addressTo", is(newTaskDto.getAddressTo())))
//                .andExpect(jsonPath("$.waybillNumber", is(newTaskDto.getWaybillNumber())))
//                .andExpect(jsonPath("$.taskStatus", is(newTaskDto.getTaskStatus().toString())));
    }

    @Test
    void updateTaskTest() throws Exception {
        //when(taskServiceMock.update(any(TaskDto.class))).thenReturn(EntityCreator.getNewTask());
//        mockMvc
//                .perform(MockMvcRequestBuilders.put("/task/update")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                                {
//                                    "taskId" : "f2efb169-73ce-4901-9d28-dbf5cc7040c2",
//                                    "addressTo" : "new address to",
//                                    "weightCargo" : 4000,
//                                    "taskStatus" : "PLANNED"
//                                }
//                                """))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.taskId", is("f2efb169-73ce-4901-9d28-dbf5cc7040c2")))
//                .andExpect(jsonPath("$.addressTo", is("new address to")))
//                .andExpect(jsonPath("$.weightCargo").value(4000))
//                .andExpect(jsonPath("$.taskStatus", is("PLANNED")));
    }

    @Test
    void deleteTaskByIdTest() throws Exception {
        // for waybillNumber - 005
        String id = "5e1d09bd-8aed-410b-a51f-2b5c45d909bd";
        mockMvc
                .perform(MockMvcRequestBuilders.get("/task/" + id))
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc
                .perform(MockMvcRequestBuilders.delete("/task/delete/" + id))
                .andExpect(status().isOk());
    }
}