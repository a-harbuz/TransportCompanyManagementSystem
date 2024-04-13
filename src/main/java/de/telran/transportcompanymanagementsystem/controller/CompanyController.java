package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;
import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import de.telran.transportcompanymanagementsystem.validation.UuidChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Validated
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/all")
    @Operation(
            summary = "Show all companies",
            description = "Getting all companies")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned companies",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Company.class)))
            })
    //http://localhost:8080/company/all
    public List<CompanyDto> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find company by Id", description = "Getting company by Id")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned Company",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Company.class))
            })
    public CompanyDto getCompanyById(@PathVariable("id") @UuidChecker @Parameter(required = true, description =
        "Company UUID") String id) {
        //http://localhost:8080/company/2d0cc985-ffdc-40de-be58-69eba564fc47
        return companyService.getCompanyById(id);
    }

    @GetMapping("/name/{nameCompany}")
    @Operation(
            summary = "Find companies which contains substring",
            description = "Getting companies which contains substring")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned Companies",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Company.class)))
            })
    public List<CompanyDto> getCompanyByNameIncludingString(@PathVariable("nameCompany") @Parameter(required = true, description =
        "Company name") String nameCompany) {
        //http://localhost:8080/company/name/Boehm-Klein
        return companyService.getCompanyByNameIncludingStringDto(nameCompany);
    }

    @PutMapping("/update/name/{nameCompany}/{newNameCompany}")
    @Operation(
            summary = "Find company by name and update name",
            description = "Updating name of company")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned Company with new name",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Company.class))
            })
    public CompanyDto updateCompanyByName(@PathVariable("nameCompany") String nameCompany,
                                    @PathVariable("newNameCompany") String newNameCompany) {
        //http://localhost:8080/company/update/name/Larson-Witting/New Larson
        return companyService.updateCompanyByName(nameCompany, newNameCompany);
    }

    @PutMapping("/update")
    @Operation(
            summary = "Update company data",
            description = "Update company data")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned updated Company",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Company.class))
            })
    public CompanyDto updateCompanyById(@RequestBody CreateUpdateCompanyDto createUpdateCompanyDto){
        //http://localhost:8080/company/update
        return companyService.updateCompanyById(createUpdateCompanyDto);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete the company by Id",
            description = "Delete the company by Id")
    @ApiResponse(
            responseCode = "200",
            description = "return responseCode = 200",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Company.class))
            })
    public void deleteCompanyById(@PathVariable("id") @UuidChecker @Parameter(required = true, description =
        "Company UUID") String id) {
        //http://localhost:8080/company/delete/0a8de57b-4ac3-43f9-9ab4-77784de2554a
        companyService.deleteCompanyById(id);
    }

    @PostMapping("/new")
    @Operation(
            summary = "Add new company",
            description = "Add new company")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned the new company",
            content = {
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Company.class))
            })
    public CompanyDto createCompany(@RequestBody CreateUpdateCompanyDto createUpdateCompanyDto)
    {
        //http://localhost:8080/new/company
        return companyService.create(createUpdateCompanyDto);
    }
}
