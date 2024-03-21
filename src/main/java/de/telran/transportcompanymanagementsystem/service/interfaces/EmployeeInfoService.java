package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;

import java.util.List;

public interface EmployeeInfoService {
    EmployeeInfo getEmployeeInfoById(String id);
    List<EmployeeInfo> getEmployeeInfoList();
    List<EmployeeInfo> getEmployeeInfoNoDriverLicense();
}
