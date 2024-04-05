package de.telran.transportcompanymanagementsystem.controller;

import de.telran.transportcompanymanagementsystem.service.interfaces.RoleService;
import de.telran.transportcompanymanagementsystem.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable("id") String id) {
        //http://localhost:8080/role/21679aa7-c43b-468d-8318-8090227c4acb
        return roleService.getRoleById(id);
    }

    @GetMapping("/all")
    public List<Role> getAllRole() {
        //http://localhost:8080/role/all
        return roleService.getAllRole();
    }
}
