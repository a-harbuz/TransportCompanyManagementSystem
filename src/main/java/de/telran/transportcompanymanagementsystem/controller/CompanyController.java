package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable("id") String id) {
        //http://localhost:8080/vehicle/21679aa7-c43b-468d-8318-8090227c4acb
        return companyService.getCompanyById(id);
    }

    @GetMapping("/name/{nameCompany}")
    public List<Company> getCompanyByName(@PathVariable("nameCompany") String nameCompany) {
        //http://localhost:8080/company/name/Boehm-Klein
        return companyService.getCompanyByName(nameCompany);
    }
}
