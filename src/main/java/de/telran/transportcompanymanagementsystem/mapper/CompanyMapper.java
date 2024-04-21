package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;
import de.telran.transportcompanymanagementsystem.entity.Company;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface CompanyMapper {
    List<CompanyDto> toDtoList(List<Company> companies);
    CompanyDto toDto(Company company);
    Company toEntity (CreateUpdateCompanyDto createCompanyDto);
}
