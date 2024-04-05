package de.telran.transportcompanymanagementsystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Transport Company Management System",
                description = "There is a prototype of the backend Transport Company Management System. <br />" +
                        "Data consist of companies, vehicles, maintenances, employees, roles, " +
                        "contracts, tasks. <br />" + "<b>Developer - Harbuz Oleksandr.</b>",
                version = "1.0.0",
                contact = @Contact(
                        //name = "Harbuz Oleksandr",
                        name = "the developer",
                        email = "pamail08@gmail.com"
                )
        )
)
//@SecurityScheme(
//        name = "JWT",
//        type = SecuritySchemeType.HTTP,
//        bearerFormat = "JWT",
//        scheme = "bearer"
//)
public class SwaggerConfig {
}
