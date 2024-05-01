package de.telran.transportcompanymanagementsystem.annotation.employee;

import de.telran.transportcompanymanagementsystem.dto.EmployeeDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
        summary = "Find employee by Id",
        description = "Find employee by Id",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "The unique UUID identifier of the employee",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "uuid")
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully returned employee",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = EmployeeDto.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Employee with this ID was not found.",
                        content = @Content(
                                mediaType = "application/json",
                                examples = @ExampleObject(
                                        value = "{\n  \"message\": \"!!!! Employee with this ID was not found.\"\n}"
                                )
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Invalid ID",
                        content = @Content(
                                mediaType = "application/json",
                                examples = @ExampleObject(
                                        value = "{\n  \"message\": \"Invalid ID\"\n}"
                                )
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetEmployeeByIdDocumentation {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
