package de.telran.transportcompanymanagementsystem.annotation.employee;

import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
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
@RequestMapping(method = RequestMethod.GET)
@Operation(
        summary = "Show all employees",
        description = "Show all employees",
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully returned employees",
                        content = @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = EmployeeDto.class))
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Employee with this argument was not found.",
                        content = @Content(
                                mediaType = "application/json",
                                examples = @ExampleObject(
                                        value = "{\n  \"message\": \"!!!! Employee with this argument was not found.\"\n}"
                                )
                        )
                ),
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetEmployeeListDocumentation {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
