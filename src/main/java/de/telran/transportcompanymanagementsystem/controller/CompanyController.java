package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") String id) {
        //http://localhost:8080/company/21679aa7-c43b-468d-8318-8090227c4acb
        return companyService.getCompanyById(id);
    }

    @GetMapping("/name/{nameCompany}")
    public List<Company> getCompanyByName(@PathVariable("nameCompany") String nameCompany) {
        //http://localhost:8080/company/name/Boehm-Klein
        return companyService.getCompanyByName(nameCompany);
    }

    @GetMapping("/name/update/{nameCompany}/{newNameCompany}")
    public Company setCompanyByName(@PathVariable("nameCompany") String nameCompany,
                                    @PathVariable("newNameCompany") String newNameCompany) {
        //http://localhost:8080/company/name/update/Larson-Witting/New Larson
        return companyService.setCompanyByName(nameCompany, newNameCompany);
    }

    @PostMapping("/new")
    public Company createCompany(@RequestBody Company company)
    {
        //http://localhost:8080/company/new
        return companyService.create(company);
    }
}
