package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.entity.Vehicle;
import de.telran.transportcompanymanagementsystem.exception.CompanyNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.VehicleNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.CompanyRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository companyRepository;

    @Override
    public Company getCompanyById(String id) {
        return companyRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new CompanyNotFoundException(ErrorMessage.COMPANY_NOT_FOUND));
    }

    @Override
    public List<Company> getCompanyByName(String companyName) {
        List<Company> companies = companyRepository.findByCompanyNameContainsIgnoreCase(companyName);
        if (!companies.isEmpty()) {
            return companies;
        } else {
            throw new CompanyNotFoundException(ErrorMessage.COMPANY_NAME_NOT_FOUND);
        }
    }

    @Override
    public Company setCompanyByName(String companyName, String newCompanyName) {
        Company company = companyRepository.findByCompanyName(companyName);
        if (company != null) {
            company.setCompanyName(newCompanyName);
            companyRepository.saveAndFlush(company);
            return company;
        } else {
            throw new CompanyNotFoundException(ErrorMessage.COMPANY_NAME_NOT_FOUND);
        }
    }

    @Override
    public void deleteCompanyById(String id) {
        companyRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new CompanyNotFoundException(ErrorMessage.COMPANY_NOT_FOUND));
        companyRepository.deleteById(UUID.fromString(id));
    }

    @Override
    public Company create(Company company) {
        return companyRepository.save(company);
    }
}
