package de.telran.transportcompanymanagementsystem.service.interfaces;

import de.telran.transportcompanymanagementsystem.entity.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(String id);
    List<Role> getAllRole();
}
