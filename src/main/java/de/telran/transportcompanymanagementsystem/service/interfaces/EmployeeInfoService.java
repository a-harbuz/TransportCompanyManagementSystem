package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.dto.EmployeeInfoDto;

import java.util.List;

public interface EmployeeInfoService {
    EmployeeInfoDto getEmployeeInfoById(String id);
    List<EmployeeInfoDto> getEmployeeInfoList();
    List<EmployeeInfoDto> getEmployeeInfoNoDriverLicense();
}
