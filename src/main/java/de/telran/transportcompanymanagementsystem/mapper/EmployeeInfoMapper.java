package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.EmployeeInfoDto;
import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface EmployeeInfoMapper {
    List<EmployeeInfoDto> toDtoList(List<EmployeeInfo> employeeInfoList);

    EmployeeInfoDto toDto(EmployeeInfo employeeInfo);

    @Mappings({
            @Mapping(target = "password", ignore = true),
            @Mapping(target = "employee", ignore = true),
            @Mapping(target = "roles", ignore = true)
    })
    EmployeeInfo toEntity (EmployeeInfoDto employeeInfoDto);
}
