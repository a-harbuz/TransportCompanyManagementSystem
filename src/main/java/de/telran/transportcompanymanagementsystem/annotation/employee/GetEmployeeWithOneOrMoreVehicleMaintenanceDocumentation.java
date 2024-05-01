package de.telran.transportcompanymanagementsystem.annotation.employee;

import de.telran.transportcompanymanagementsystem.dto.EmployeeWithVehicleAndMaintenanceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
        summary = "Looks for employees who contain tasks in which vehicles contain one or more maintenance jobs.",
        description = "Looks for employees who contain tasks in which vehicles contain one or more maintenance jobs.")
@ApiResponse(
        responseCode = "200",
        description = "Successfully returned employees with maintenances",
        content = {
                @Content(
                        mediaType = "application/json",
                        array = @ArraySchema(
                                schema = @Schema(implementation = EmployeeWithVehicleAndMaintenanceDto.class)))
        })
public @interface GetEmployeeWithOneOrMoreVehicleMaintenanceDocumentation {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
