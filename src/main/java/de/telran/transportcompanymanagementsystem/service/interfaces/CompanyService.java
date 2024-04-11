package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;

import java.util.List;

public interface CompanyService {
    CompanyDto getCompanyById(String id);
    List<CompanyDto> getCompanies();
    List<CompanyDto> getCompanyByNameIncludingStringDto(String companyName);
    CompanyDto updateCompanyByName(String nameCompany, String newNameCompany);
    CompanyDto updateCompanyById (CreateUpdateCompanyDto createUpdateCompanyDto);
    void deleteCompanyById (String id);
    CompanyDto create (CreateUpdateCompanyDto createUpdateCompanyDto);
}
