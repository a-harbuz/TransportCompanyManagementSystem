package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.service.interfaces.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping("/{id}")
//    public Account getAccountById(@PathVariable("id") String id) {
//        return accountService.getAccountById(id);
//    }
    public String getEmployeeById(@PathVariable("id") String id) {
        //1c9859bd-8d9b-49e0-88d7-58f8f3c1c4b3
        //http://localhost:8080/employee/1c9859bd-8d9b-49e0-88d7-58f8f3c1c4b3
        //return (id.equals("1c9859bd-8d9b-49e0-88d7-58f8f3c1c4b3")) ? "Есть такой Работник" : "User(id): " + id;
        //В контроллерах часто внедряются сервисы для выполнения бизнес-логики.
        //перенесли реализацию interfaces EmployeeService
        return employeeService.getEmployeeById(id);
    }
}
