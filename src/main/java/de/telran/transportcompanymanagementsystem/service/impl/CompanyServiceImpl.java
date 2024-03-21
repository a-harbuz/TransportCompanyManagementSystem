package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.exception.CompanyNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errorMessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.CompanyRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
