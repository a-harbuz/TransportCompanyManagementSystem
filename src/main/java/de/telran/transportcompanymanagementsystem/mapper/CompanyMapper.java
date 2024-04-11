package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.CompanyDto;
import de.telran.transportcompanymanagementsystem.dto.CreateUpdateCompanyDto;
import de.telran.transportcompanymanagementsystem.entity.Company;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.List;

//@Mapper(componentModel = "spring") //, uses = ProductMapper.class, imports = Random.class)
@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface CompanyMapper {
//    @Mapping(source = "companyName", target = "companyName")
//    @Mapping(source = "contactFirstName", target = "contactFirstName")
//    @Mapping(source = "contactLastName", target = "contactLastName")
//    @Mapping(source = "address", target = "address")
//    @Mapping(source = "email", target = "email")
//    @Mapping(source = "phone", target = "phone")
    List<CompanyDto> toDtoList(List<Company> companies);
    CompanyDto toDto(Company company);
    Company toEntity (CreateUpdateCompanyDto createCompanyDto);
}
