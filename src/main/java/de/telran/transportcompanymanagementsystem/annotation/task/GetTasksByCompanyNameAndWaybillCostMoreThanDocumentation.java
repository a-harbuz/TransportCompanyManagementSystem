package de.telran.transportcompanymanagementsystem.annotation.task;

import de.telran.transportcompanymanagementsystem.entity.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
        summary = "Find tasks by company name and waybill cost more than value",
        description = "Find tasks by company name and waybill cost more than value",
        parameters = {
                @Parameter(
                        name = "companyName",
                        description = "The name of company",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "string", format = "string")
                ),
                @Parameter(
                        name = "waybillCost",
                        description = "The waybill cost value",
                        required = true,
                        in = ParameterIn.PATH,
                        schema = @Schema(type = "number", format = "BigDecimal")
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Successfully returned tasks",
                        content = @Content(
                                mediaType = "application/json",
                                array = @ArraySchema(schema = @Schema(implementation = Task.class))
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Task with this argument was not found.",
                        content = @Content(
                                mediaType = "application/json",
                                examples = @ExampleObject(
                                        value = "{\n  \"message\": \"!!!! Task with this argument was not found.\"\n}"
                                )
                        )
                )
        },
        security = {
                @SecurityRequirement(name = "safety requirements")
        }
)
public @interface GetTasksByCompanyNameAndWaybillCostMoreThanDocumentation {
    @AliasFor(annotation = RequestMapping.class, attribute = "path")
    String[] path() default {};
}
