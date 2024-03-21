package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.entity.EmployeeInfo;
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
    public EmployeeInfo getEmployeeInfoById(@PathVariable("id") String id) {
        //http://localhost:8080/vehicle/21679aa7-c43b-468d-8318-8090227c4acb
        return employeeInfoService.getEmployeeInfoById(id);
    }

    @GetMapping("/all")
    public List<EmployeeInfo> getEmployeeInfoAll() {
        //http://localhost:8080/employeeinfo/all
        return employeeInfoService.getEmployeeInfoList();
    }

    @GetMapping("/noDriverLicense")
    public List<EmployeeInfo> getEmployeeInfoNoDriverLicense() {
        //http://localhost:8080/employeeinfo/noDriverLicense
        return employeeInfoService.getEmployeeInfoNoDriverLicense();
    }
}
