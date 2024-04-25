package de.telran.transportcompanymanagementsystem.mapper;

import de.telran.transportcompanymanagementsystem.dto.EmployeeInfoDto;
import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.List;

@Mapper(componentModel = "spring", imports = Timestamp.class)
public interface EmployeeInfoMapper {
    List<EmployeeInfoDto> toDtoList(List<EmployeeInfo> employeeInfoList);
    EmployeeInfoDto toDto(EmployeeInfo employeeInfo);
    EmployeeInfo toEntity (EmployeeInfoDto employeeInfoDto);
}
