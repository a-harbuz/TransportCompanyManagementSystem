package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import util.CheckUuidPattern;
import util.EntityCreator;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Test class for RoleController")
class RoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getRoleById() throws Exception {
        Role role = EntityCreator.getRole();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/role/" + role.getRId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rid", is(role.getRId().toString())))
                .andExpect(jsonPath("$.roleName", is(role.getRoleName())));
    }

    @Test
    void getRoleByIdNegativeTest() throws Exception {
        mockMvc
                .perform(MockMvcRequestBuilders.get("/role/26e41ad9-0482-4808-9dbb-c917631f1b5c"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getRoleListTest() throws Exception {
        String uuidPattern = CheckUuidPattern.getUuidPattern();
        mockMvc
                .perform(MockMvcRequestBuilders.get("/role/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(MockMvcResultMatchers
                        .jsonPath("$[0].rid", matchesPattern(uuidPattern)));
    }
}