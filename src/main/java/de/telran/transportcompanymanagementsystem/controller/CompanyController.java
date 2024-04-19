package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.annotation.company.*;
import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;
import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import de.telran.transportcompanymanagementsystem.validation.UuidChecker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
    @GetCompaniesDocumentation
    //http://localhost:8080/company/all
    public List<CompanyDto> getCompanies() {
        return companyService.getCompanies();
    }

    @GetMapping("/{id}")
    @GetCompanyByIdDocumentation
    public CompanyDto getCompanyById(@PathVariable("id") @UuidChecker String id) {
        //http://localhost:8080/company/2d0cc985-ffdc-40de-be58-69eba564fc47
        return companyService.getCompanyById(id);
    }

    @GetMapping("/name/{nameCompany}")
    @GetCompanyByNameIncludingStringDocumentation
    public List<CompanyDto> getCompanyByNameIncludingString(@PathVariable("nameCompany") String nameCompany) {
        //http://localhost:8080/company/name/Boehm-Klein
        return companyService.getCompanyByNameIncludingStringDto(nameCompany);
    }

    @PutMapping("/update/name/{nameCompany}/{newNameCompany}")
    @UpdateCompanyByNameDocumentation
    public CompanyDto updateCompanyByName(@PathVariable("nameCompany") String nameCompany,
                                    @PathVariable("newNameCompany") String newNameCompany) {
        //http://localhost:8080/company/update/name/Larson-Witting/New Larson
        return companyService.updateCompanyByName(nameCompany, newNameCompany);
    }

    @PutMapping("/update")
    @UpdateCompanyByIdDocumentation
    public CompanyDto updateCompanyById(@RequestBody CreateUpdateCompanyDto createUpdateCompanyDto){
        //http://localhost:8080/company/update
        return companyService.updateCompanyById(createUpdateCompanyDto);
    }

    @DeleteMapping("/delete/{id}")
    @DeleteCompanyByIdDocumentation
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
    @CreateCompanyDocumentation
    public CompanyDto createCompany(@RequestBody CreateUpdateCompanyDto createUpdateCompanyDto)
    {
        //http://localhost:8080/new/company
        return companyService.create(createUpdateCompanyDto);
    }
}
