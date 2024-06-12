package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;
import de.telran.transportcompanymanagementsystem.entity.Company;
import org.mapstruct.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface CompanyMapper {
    List<CompanyDto> toDtoList(List<Company> companies);

    CompanyDto toDto(Company company);

    @Mappings({
            @Mapping(target = "createdAt", ignore = true),
            @Mapping(target = "maintenances", ignore = true),
            @Mapping(target = "contracts", ignore = true),
            @Mapping(target = "tasks", ignore = true)
    })
    Company toEntity (CreateUpdateCompanyDto createCompanyDto);

    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void update(CreateUpdateCompanyDto createUpdateCompanyDto, @MappingTarget Company company);
}
