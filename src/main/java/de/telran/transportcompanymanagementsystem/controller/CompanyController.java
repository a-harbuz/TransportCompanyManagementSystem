package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import de.telran.transportcompanymanagementsystem.validation.UuidChecker;
import io.swagger.v3.oas.annotations.Operation;
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
    public Company getCompanyById(@PathVariable("id") @UuidChecker String id) {
        //http://localhost:8080/company/2d0cc985-ffdc-40de-be58-69eba564fc47
        return companyService.getCompanyById(id);
    }

    @GetMapping("/name/{nameCompany}")
    @Operation(
            summary = "Find companies by contained substring",
            description = "Getting companies by contained substring")
    @ApiResponse(
            responseCode = "200",
            description = "Successfully returned Companies",
            content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Company.class)))
            })
    public List<Company> getCompanyByName(@PathVariable("nameCompany") String nameCompany) {
        //http://localhost:8080/company/name/Boehm-Klein
        return companyService.getCompanyByName(nameCompany);
    }

    @PutMapping("/name/update/{nameCompany}/{newNameCompany}")
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
    public Company setCompanyByName(@PathVariable("nameCompany") String nameCompany,
                                    @PathVariable("newNameCompany") String newNameCompany) {
        //http://localhost:8080/company/name/update/Larson-Witting/New Larson
        return companyService.setCompanyByName(nameCompany, newNameCompany);
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
    public void deleteCompanyById(@PathVariable("id") @UuidChecker String id) {
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
    public Company createCompany(@RequestBody Company company)
    {
        //http://localhost:8080/company/new
        return companyService.create(company);
    }
}
