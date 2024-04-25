package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;
import de.telran.transportcompanymanagementsystem.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import util.EntityCreator;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CompanyMapperTest {

    @Autowired
    private CompanyMapper companyMapper;
    @Test
    void toDtoList() {
        Company company1 = EntityCreator.getCompany();
        Company company2 = EntityCreator.getNewCompany();

        List<CompanyDto> companyDtoList = companyMapper.toDtoList(List.of(company1,company2));

        assertNotNull(companyDtoList);
        assertEquals(companyDtoList.get(0).getCompanyId(), company1.getCompanyId());
        assertEquals(companyDtoList.get(0).getCompanyName(), company1.getCompanyName());
        assertEquals(companyDtoList.get(1).getCompanyId(), company2.getCompanyId());
        assertEquals(companyDtoList.get(1).getCompanyName(), company2.getCompanyName());
    }

    @Test
    void toDto() {
        Company company = new Company();
        company.setCompanyId(UUID.randomUUID());
        company.setCompanyName("Company name");

        CompanyDto dto = companyMapper.toDto(company);

        assertNotNull(dto);
        assertEquals(company.getCompanyId(), dto.getCompanyId());
        assertEquals(company.getCompanyName(), dto.getCompanyName());
    }

    @Test
    void toEntity() {
        CreateUpdateCompanyDto createCompanyDto = new CreateUpdateCompanyDto();
        createCompanyDto.setCompanyId(UUID.randomUUID());
        createCompanyDto.setCompanyName("Company name");
        createCompanyDto.setPhone("Company phone");

        Company entity = companyMapper.toEntity(createCompanyDto);

        assertNotNull(entity);
        assertEquals(createCompanyDto.getCompanyId(), entity.getCompanyId());
        assertEquals(createCompanyDto.getCompanyName(), entity.getCompanyName());
    }
}