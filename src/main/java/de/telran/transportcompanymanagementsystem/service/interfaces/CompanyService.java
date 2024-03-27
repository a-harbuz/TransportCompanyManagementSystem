package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Company;

import java.util.List;

public interface CompanyService {
    Company getCompanyById(String id);
    List<Company> getCompanyByName(String companyName);
    Company setCompanyByName(String nameCompany, String newNameCompany);
    Company create (Company company);
    void deleteCompanyById (String id);
}
