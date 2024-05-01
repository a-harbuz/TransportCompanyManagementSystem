package de.telran.transportcompanymanagementsystem.annotation.employee;

import de.telran.transportcompanymanagementsystem.dto.EmployeeAfterRegistrationDto;
import de.telran.transportcompanymanagementsystem.dto.EmployeeRegistrationDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(method = RequestMethod.POST)
@Operation(
        summary = "Add new employee",
        description = "Add new employee",
        requestBody = @RequestBody(
                description = "The employee to be created",
                required = true,
                content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = EmployeeRegistrationDto.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Successfully returned employee",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = EmployeeAfterRegistrationDto.class)
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface CreateEmployeeDocumentation {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
