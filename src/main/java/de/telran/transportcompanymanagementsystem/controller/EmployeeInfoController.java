package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.dto.EmployeeInfoDto;
import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/employeeinfo")
@RequiredArgsConstructor
public class EmployeeInfoController {

    private final EmployeeInfoService employeeInfoService;

    @GetMapping("/{id}")
    public EmployeeInfoDto getEmployeeInfoById(@PathVariable("id") String id) {
        //http://localhost:8080/employeeinfo/18907be0-b307-49d6-8f4c-27f9c26589d4
        return employeeInfoService.getEmployeeInfoById(id);
    }

    @GetMapping("/all")
    public List<EmployeeInfoDto> getEmployeeInfoList() {
        //http://localhost:8080/employeeinfo/all
        return employeeInfoService.getEmployeeInfoList();
    }

    @GetMapping("/noDriverLicense")
    public List<EmployeeInfoDto> getEmployeeInfoNoDriverLicense() {
        //http://localhost:8080/employeeinfo/noDriverLicense
        return employeeInfoService.getEmployeeInfoNoDriverLicense();
    }
}
