package de.telran.transportcompanymanagementsystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
@WithMockUser(username = "admin", password = "1234", roles = "DEVELOPER")
class TransportCompanyManagementSystemApplicationTest {

    @Test
    void contextLoads() {
    }
}