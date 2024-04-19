package de.telran.transportcompanymanagementsystem.annotation.company;

import de.telran.transportcompanymanagementsystem.entity.Company;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Find companies which contains substring",
        description = "Getting companies which contains substring",
        parameters = {
                @Parameter(
                        name = "nameCompany",
                        description = "Company name",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "string")
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully returned Company",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Company.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Company with this name was not found."
                ),
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetCompanyByNameIncludingStringDocumentation {
}
