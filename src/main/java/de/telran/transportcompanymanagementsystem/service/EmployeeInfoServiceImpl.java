package de.telran.transportcompanymanagementsystem.service;

import de.telran.transportcompanymanagementsystem.dto.EmployeeInfoDto;
import de.telran.transportcompanymanagementsystem.entity.enums.DrivingLicenseCategory;
import de.telran.transportcompanymanagementsystem.exception.EmployeeNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.mapper.EmployeeInfoMapper;
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
    private final EmployeeInfoMapper employeeInfoMapper;

    @Override
    public EmployeeInfoDto getEmployeeInfoById(String id) {
        return employeeInfoMapper.toDto(employeeInfoRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new EmployeeNotFoundException(ErrorMessage.EMPLOYEE_NOT_FOUND)));
    }

    @Override
    public List<EmployeeInfoDto> getEmployeeInfoList() {
        return employeeInfoMapper.toDtoList(employeeInfoRepository.findAll());
    }

    @Override
    public List<EmployeeInfoDto> getEmployeeInfoNoDriverLicense() {
        return employeeInfoMapper.toDtoList(employeeInfoRepository.findAll().stream()
                .filter(x->x.getDrivingLicenseCategory().equals(DrivingLicenseCategory.ABSENT))
                .toList());
    }
}
