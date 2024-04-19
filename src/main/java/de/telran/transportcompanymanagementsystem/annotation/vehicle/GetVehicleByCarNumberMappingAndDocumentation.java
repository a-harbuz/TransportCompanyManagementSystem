package de.telran.transportcompanymanagementsystem.annotation.vehicle;

import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
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
        summary = "Find vehicle by car number",
        description = "Getting vehicle by car number",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "Car number of vehicle",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "string")
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully returned vehicle",
                        content = @Content(
                                mediaType = "application/json",
                                schema = @Schema(implementation = Vehicle.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Vehicle with this car number was not found."
                ),
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetVehicleByCarNumberMappingAndDocumentation {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
