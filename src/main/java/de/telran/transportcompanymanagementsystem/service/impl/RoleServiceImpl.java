package de.telran.transportcompanymanagementsystem.service.impl;

import de.telran.transportcompanymanagementsystem.entity.Role;
import de.telran.transportcompanymanagementsystem.exception.DataNotFoundException;
import de.telran.transportcompanymanagementsystem.exception.errormessage.ErrorMessage;
import de.telran.transportcompanymanagementsystem.repository.RoleRepository;
import de.telran.transportcompanymanagementsystem.service.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getRoleById(String id) {
        return roleRepository.findById(UUID.fromString(id))
                .orElseThrow(()-> new DataNotFoundException(ErrorMessage.DATA_NOT_FOUND));
    }

    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }
}
