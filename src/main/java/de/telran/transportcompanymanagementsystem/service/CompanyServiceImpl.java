package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;
import de.telran.transportcompanymanagementsystem.entity.Company;
import de.telran.transportcompanymanagementsystem.exception.CompanyBadRequestException;
import de.telran.transportcompanymanagementsystem.exception.CompanyNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.CompanyMapper;
import de.telran.transportcompanymanagementsystem.repository.CompanyRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public List<CompanyDto> getCompanies() {
        return companyMapper.toDtoList(companyRepository.findAll());
    }

    @Override
    public CompanyDto getCompanyById(String id) {
        Company company = companyRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new CompanyNotFoundException(ErrorMessage.COMPANY_NOT_FOUND));
        return companyMapper.toDto(company);
    }

    @Override
    public List<CompanyDto> getCompanyByNameIncludingStringDto(String companyName) {
        List<Company> companies = companyRepository.findByCompanyNameContainsIgnoreCase(companyName);
        if (!companies.isEmpty()) {
            return companyMapper.toDtoList(companies);
        } else {
            throw new CompanyNotFoundException(ErrorMessage.COMPANY_NAME_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public CompanyDto updateCompanyByName(String companyName, String newCompanyName) {
        Company company = companyRepository.findByCompanyName(companyName);
        if (company != null) {
            company.setCompanyName(newCompanyName);
            return companyMapper.toDto(companyRepository.saveAndFlush(company));
        } else {
            throw new CompanyNotFoundException(ErrorMessage.COMPANY_NAME_NOT_FOUND);
        }
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    public CompanyDto updateCompanyById(CreateUpdateCompanyDto createUpdateCompanyDto) {
        if (createUpdateCompanyDto.getCompanyId()==null)
            throw new CompanyNotFoundException(ErrorMessage.COMPANY_ID_IS_ABSENT);
        Optional<Company> companyOptional = companyRepository.findById(createUpdateCompanyDto.getCompanyId());
        if (companyOptional.isEmpty()) throw new CompanyNotFoundException(ErrorMessage.COMPANY_NOT_FOUND);
        Company company = companyOptional.get();
        if (createUpdateCompanyDto.getCompanyName()!=null)
            company.setCompanyName(createUpdateCompanyDto.getCompanyName());
        if (createUpdateCompanyDto.getContactFirstName()!=null)
            company.setContactFirstName(createUpdateCompanyDto.getContactFirstName());
        if (createUpdateCompanyDto.getContactLastName()!=null)
            company.setContactLastName(createUpdateCompanyDto.getContactLastName());
        if (createUpdateCompanyDto.getEmail()!=null)
            company.setEmail(createUpdateCompanyDto.getEmail());
        if (createUpdateCompanyDto.getAddress()!=null)
            company.setAddress(createUpdateCompanyDto.getAddress());
        if (createUpdateCompanyDto.getPhone()!=null)
            company.setPhone(createUpdateCompanyDto.getPhone());
        return companyMapper.toDto(companyRepository.saveAndFlush(company));
    }

    @Override
    @Transactional
    public void deleteCompanyById(String id) {
        companyRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new CompanyNotFoundException(ErrorMessage.COMPANY_NOT_FOUND));
        companyRepository.deleteById(UUID.fromString(id));
    }

    @Override
    @Transactional
    public CompanyDto create(CreateUpdateCompanyDto createUpdateCompanyDto) {
        if (createUpdateCompanyDto.getCompanyName().isEmpty())
            throw new CompanyBadRequestException(ErrorMessage.COMPANY_NAME_CAN_NOT_BE_EMPTY);
        Company findCompany = companyRepository.findByCompanyName(createUpdateCompanyDto.getCompanyName());
        if (findCompany != null) throw new CompanyBadRequestException(ErrorMessage.COMPANY_NAME_ALREADY_PRESENT);
        Company company = companyMapper.toEntity(createUpdateCompanyDto);
        company.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return companyMapper.toDto(companyRepository.save(company));
    }
}
