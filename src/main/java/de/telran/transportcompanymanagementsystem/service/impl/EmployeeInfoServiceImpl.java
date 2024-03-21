package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
import de.telran.transportcompanymanagementsystem.exception.EmployeeNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errorMessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.EmployeeInfoRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmployeeInfoServiceImpl implements EmployeeInfoService {

    private final EmployeeInfoRepository employeeInfoRepository;

    @Override
    public EmployeeInfo getEmployeeInfoById(String id) {
        return employeeInfoRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public List<EmployeeInfo> getEmployeeInfoList() {
        return employeeInfoRepository.findAll();
    }

    @Override
    public List<EmployeeInfo> getEmployeeInfoNoDriverLicense() {
        return employeeInfoRepository.findAll().stream()
                .filter(x->x.getDrivingLicenseCategory().equals(DrivingLicenseCategory.ABSENT))
                .toList();
    }
}
