package de.telran.transportcompanymanagementsystem.annotation.company;

import de.telran.transportcompanymanagementsystem.entity.Company;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Create the company",
        description = "Create the company",
        requestBody = @RequestBody(
                description = "The company to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = Company.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Successfully returned company",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Company.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface CreateCompanyDocumentation {
}
