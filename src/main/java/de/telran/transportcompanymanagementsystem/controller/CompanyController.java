package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import de.telran.transportcompanymanagementsystem.validation.UuidChecker;
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
    public Company getCompanyById(@PathVariable("id") @UuidChecker String id) {
        //http://localhost:8080/company/2d0cc985-ffdc-40de-be58-69eba564fc47
        return companyService.getCompanyById(id);
    }

    @GetMapping("/name/{nameCompany}")
    public List<Company> getCompanyByName(@PathVariable("nameCompany") String nameCompany) {
        //http://localhost:8080/company/name/Boehm-Klein
        return companyService.getCompanyByName(nameCompany);
    }

    @PutMapping("/name/update/{nameCompany}/{newNameCompany}")
    public Company setCompanyByName(@PathVariable("nameCompany") String nameCompany,
                                    @PathVariable("newNameCompany") String newNameCompany) {
        //http://localhost:8080/company/name/update/Larson-Witting/New Larson
        return companyService.setCompanyByName(nameCompany, newNameCompany);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCompanyById(@PathVariable("id") @UuidChecker String id) {
        //http://localhost:8080/company/delete/0a8de57b-4ac3-43f9-9ab4-77784de2554a
        companyService.deleteCompanyById(id);
    }

    @PostMapping("/new")
    public Company createCompany(@RequestBody Company company)
    {
        //http://localhost:8080/company/new
        return companyService.create(company);
    }
}
